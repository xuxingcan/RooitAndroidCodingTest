package com.cyning.rooitandroidcodingtest.data.net.bean

data class NewsResponse(
    val articles: List<ArticleNet?>?,
    val status: String?,
    val totalResults: Int?
)