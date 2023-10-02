package com.abdoulaye.jcdecaux.di

import com.abdoulaye.jcdecaux.model.ApiServices
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun injectApi(service: ApiServices)
}