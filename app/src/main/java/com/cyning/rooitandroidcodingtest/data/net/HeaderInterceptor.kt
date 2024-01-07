package com.cyning.rooitandroidcodingtest.data.net

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val newRequest = oldRequest.newBuilder()
            .url(
                oldRequest.url
                    .newBuilder()
                    .addQueryParameter("apiKey", "f5c3b1c8d6bf483da1d4fcf41a4ff33c")
                    .build()
            )
            .build()
        return chain.proceed(newRequest)
    }
}