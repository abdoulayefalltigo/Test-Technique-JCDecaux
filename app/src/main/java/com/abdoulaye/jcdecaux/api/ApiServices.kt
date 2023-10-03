package com.abdoulaye.jcdecaux.api

import com.abdoulaye.jcdecaux.di.DaggerApiComponent
import com.abdoulaye.jcdecaux.model.BicycleStation
import io.reactivex.Single
import javax.inject.Inject

class ApiServices {
    @Inject
    lateinit var api: ApiBicycleStations

    init {
        DaggerApiComponent.create().injectApi(this)
    }
    fun getBikeStations(): Single<List<BicycleStation>> {
        return api.getBikeStation()
    }

}