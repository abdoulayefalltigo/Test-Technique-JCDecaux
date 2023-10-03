package com.abdoulaye.jcdecaux.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.abdoulaye.jcdecaux.di.AppModule
import com.abdoulaye.jcdecaux.di.DaggerViewModelComponent
import com.abdoulaye.jcdecaux.api.ApiServices
import com.abdoulaye.jcdecaux.model.BicycleStation

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ListBCStationsViewModel(application: Application): AndroidViewModel(application) {

    constructor(application: Application, test: Boolean= true): this(application) {
        injected = true
    }

    val bicycleStations by lazy { MutableLiveData<List<BicycleStation>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var apiServices: ApiServices

    private var injected = false

    private fun inject() {
        if(!injected) {
            DaggerViewModelComponent.builder().appModule(AppModule(getApplication()))
                .build().injectViewBicycleStationsApi(this)
        }
    }

    fun refreshData() {
        inject()
        loading.value  = true
        getBikeStations()
    }

    private fun getBikeStations() {
        disposable.add(
            apiServices.getBikeStations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<List<BicycleStation>>(){
                    override fun onSuccess(listStations: List<BicycleStation>) {
                        loadError.value = false
                        bicycleStations.value = listStations
                        loading.value = false

                        return

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        bicycleStations.value = null
                        loadError.value = true
                    }

                })
        )

    }


        override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}