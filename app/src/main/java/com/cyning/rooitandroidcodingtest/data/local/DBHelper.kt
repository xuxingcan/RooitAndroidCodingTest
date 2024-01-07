package com.cyning.rooitandroidcodingtest.data.local

import com.cyning.rooitandroidcodingtest.data.local.bean.Article
import com.cyning.rooitandroidcodingtest.data.local.bean.ArticleSource
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.query.Sort

object DBHelper {
    val realm =
        Realm.open(RealmConfiguration.create(schema = setOf(Article::class, ArticleSource::class)))

    fun getArticleList() = realm.query(Article::class).sort("publishedAt", Sort.DESCENDING).asFlow()

    suspend fun updateArticleList(articles: List<Article>) = realm.write {
        articles.forEach { copyToRealm(it, UpdatePolicy.ALL) }
    }
}