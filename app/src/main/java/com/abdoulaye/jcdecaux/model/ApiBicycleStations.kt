package com.abdoulaye.jcdecaux.model

import com.abdoulaye.jcdecaux.util.Const.GET_STATIONS
import io.reactivex.Single
import retrofit2.http.GET

interface ApiBicycleStations {
    @GET(GET_STATIONS)
    fun getBikeStation(): Single<List<BicycleStation>>
}