package com.abdoulaye.jcdecaux.di

import com.abdoulaye.jcdecaux.model.ApiBicycleStations
import com.abdoulaye.jcdecaux.model.ApiServices
import com.abdoulaye.jcdecaux.model.BicycleStationInterceptor
import com.abdoulaye.jcdecaux.model.CacheInterceptor
import com.abdoulaye.jcdecaux.util.Const.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class ApiModule {
    @Provides
    fun provideBicycleStationsApi(): ApiBicycleStations {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(BicycleStationInterceptor())
        builder.addNetworkInterceptor(CacheInterceptor())

        val client = builder.build()

        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(ApiBicycleStations::class.java)
    }

    @Provides
    open fun provideApiService(): ApiServices {
        return ApiServices()
    }
}