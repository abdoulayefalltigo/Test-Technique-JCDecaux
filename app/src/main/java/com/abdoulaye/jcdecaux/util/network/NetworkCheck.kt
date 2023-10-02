package com.abdoulaye.jcdecaux.util.network

import android.app.Application
import com.abdoulaye.jcdecaux.BuildConfig
import timber.log.Timber

abstract class NetworkCheck : Application() {
    private var instance: NetworkCheck? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.i("Creating our Application")
    }

}

