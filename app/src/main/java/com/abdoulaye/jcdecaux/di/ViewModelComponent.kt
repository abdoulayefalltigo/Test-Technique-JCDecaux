package com.abdoulaye.jcdecaux.di

import com.abdoulaye.jcdecaux.viewmodel.ListBCStationsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface ViewModelComponent {
    fun injectViewBicycleStationsApi(viewModel: ListBCStationsViewModel)
}