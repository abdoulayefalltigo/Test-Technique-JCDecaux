package com.abdoulaye.jcdecaux

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abdoulaye.jcdecaux.di.AppModule
import com.abdoulaye.jcdecaux.di.DaggerViewModelComponent
import com.abdoulaye.jcdecaux.api.ApiServices
import com.abdoulaye.jcdecaux.model.BicycleStation
import com.abdoulaye.jcdecaux.viewmodel.ListBCStationsViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ListBicycleStationsViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var services: ApiServices

    private val application = Mockito.mock(Application::class.java)

    private var viewModel = ListBCStationsViewModel(application, true)


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        DaggerViewModelComponent.builder().appModule(AppModule(application))
            .apiModule(ApiModuleTest(services))
            .build()
            .injectViewBicycleStationsApi(viewModel)
    }

    @Test
    fun getBCStations() {
        val bicycleStation = BicycleStation(9087, "toulouse", "00055 - SAINT-SERNIN - G. ARNOULT",
            "2 RUE GATIEN ARNOULT", null, null,
            null, null,
            null, null, null, null, null)

        val listBicycleStations = listOf(bicycleStation)

        val testSingle = Single.just(listBicycleStations)

        Mockito.`when`(services.getBikeStations()).thenReturn(testSingle)

        viewModel.refreshData()

        Assert.assertEquals(1, viewModel.bicycleStations.value?.size)
        Assert.assertEquals(false, viewModel.loading.value)
        Assert.assertEquals(false, viewModel.loadError.value)

    }

    @Test
    fun TestSingle() {
        val testSingle = Single.error<List<BicycleStation>>(Throwable())
        Mockito.`when`(services.getBikeStations()).thenReturn(testSingle)
        viewModel.refreshData()
        Assert.assertEquals(null, viewModel.bicycleStations.value?.size)
        Assert.assertEquals(false, viewModel.loading.value)
        Assert.assertEquals(true, viewModel.loadError.value)
    }

    @Before
    fun RxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker({ it.run()}, true)
            }
        }

        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

}