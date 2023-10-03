package com.abdoulaye.jcdecaux.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.abdoulaye.jcdecaux.api.LocationLiveData

class LocationViewModel(application: Application) : AndroidViewModel(application) {
    private val locationData = LocationLiveData(application)
    fun getLocationInfos() = locationData
}