package com.cyning.rooitandroidcodingtest.data.net

import com.cyning.rooitandroidcodingtest.data.net.bean.NewsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("v2/top-headlines?country=us")
    suspend fun getNews(): NewsResponse
}