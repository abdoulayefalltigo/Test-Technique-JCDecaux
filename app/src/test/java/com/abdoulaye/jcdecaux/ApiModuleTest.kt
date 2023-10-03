package com.abdoulaye.jcdecaux


import com.abdoulaye.jcdecaux.di.ApiModule
import com.abdoulaye.jcdecaux.model.ApiServices

class ApiModuleTest(private val mockServices: ApiServices): ApiModule() {
    override fun provideApiService(): ApiServices {
        return mockServices
    }
}