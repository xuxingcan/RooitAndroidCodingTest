package com.cyning.rooitandroidcodingtest.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyning.rooitandroidcodingtest.data.DataManager
import com.cyning.rooitandroidcodingtest.data.local.bean.Article
import com.cyning.rooitandroidcodingtest.data.local.bean.ArticleSource
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.UpdatedResults
import kotlinx.coroutines.launch

/**
 * we use data from sqlite first, so that we can show some cached news immediately
 * then we hit the api to get newest news, once they come back, save to sqlite and refresh UI
 */
class MainViewModel(private val dataManager: DataManager) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>(false)
    val articleList = MutableLiveData<List<Article>>()

    fun queryData() {
        isLoading.value = true
        viewModelScope.launch {
            observerArticle()//read cache first, and then observer the database
        }
        viewModelScope.launch {
            refreshNews()//read the newest news, and save or update it to database
        }
    }

    private suspend fun refreshNews() {
        val result = dataManager.getNewsArticleFromNet()
        if (result.status == "ok") {
            result.articles?.filterNotNull()?.map {
                val article = Article()
                article.author = it.author.orEmpty()
                article.content = it.content.orEmpty()
                article.description = it.description.orEmpty()
                article.publishedAt = it.publishedAt.orEmpty()
                article.source = it.source?.let { articleSourceNet ->
                    ArticleSource().also {
                        it.id = articleSourceNet.id.orEmpty()
                        it.name = articleSourceNet.name.orEmpty()
                    }
                }
                article.title = it.title.orEmpty()
                article.url = it.url.orEmpty()
                article.urlToImage = it.urlToImage.orEmpty()
                article
            }?.also {
                val list = ArrayList(it)
                //To simulate new additions, that we can remove 3 item first time, then delete the following code,
//                list.removeAt(0)
//                list.removeAt(0)
//                list.removeAt(0)
                dataManager.updateArticleList(list)
            }
        }
    }

    private suspend fun observerArticle() {
        dataManager.getArticleList()
            .collect { resultsChange: ResultsChange<Article> ->
                when (resultsChange) {
                    is InitialResults -> {
                        articleList.value = resultsChange.list
                        isLoading.value = false
                    }

                    is UpdatedResults -> {//when the data is changed
                        articleList.value = resultsChange.list
                        isLoading.value = false
                    }
                }
            }
    }
}