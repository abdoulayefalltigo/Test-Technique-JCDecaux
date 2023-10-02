package com.abdoulaye.jcdecaux.model

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class BicycleStationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            // TODO: Use your API Key provided by CoinMarketCap Professional API Developer Portal.
            .build()
        return chain.proceed(newRequest)
    }
}