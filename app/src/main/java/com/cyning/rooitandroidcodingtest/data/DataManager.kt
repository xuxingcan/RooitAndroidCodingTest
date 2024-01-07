package com.cyning.rooitandroidcodingtest.data

import com.cyning.rooitandroidcodingtest.data.local.DBHelper
import com.cyning.rooitandroidcodingtest.data.local.bean.Article
import com.cyning.rooitandroidcodingtest.data.net.ApiHelper

/**
 * the repo external interface
 * get/update to sqlite and net
 */
class DataManager {
    fun getArticleList() = DBHelper.getArticleList()
    suspend fun updateArticleList(articles: List<Article>) = DBHelper.updateArticleList(articles)

    suspend fun getNewsArticleFromNet() = ApiHelper.service.getNews()
}