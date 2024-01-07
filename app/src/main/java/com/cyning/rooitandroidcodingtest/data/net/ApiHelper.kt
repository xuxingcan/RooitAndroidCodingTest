package com.cyning.rooitandroidcodingtest.data.net

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiHelper {
    val service by lazy {
        val builder = OkHttpClient().newBuilder()
        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(15, TimeUnit.SECONDS)
        builder.writeTimeout(15, TimeUnit.SECONDS)
        builder.addNetworkInterceptor(
            HttpLoggingInterceptor { message ->
                Log.d("HttpLoggingInterceptor", message)
            }.setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        )
        builder.addInterceptor(HeaderInterceptor())
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java) as ApiService
    }
}