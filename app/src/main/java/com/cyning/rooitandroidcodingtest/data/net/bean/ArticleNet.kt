package com.cyning.rooitandroidcodingtest.data.net.bean

data class ArticleNet(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: ArticleSourceNet?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)