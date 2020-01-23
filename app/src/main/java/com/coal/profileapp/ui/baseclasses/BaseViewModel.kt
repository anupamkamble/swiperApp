package com.coal.profileapp.ui.baseclasses

import androidx.lifecycle.ViewModel
import com.coal.profileapp.dataModel.remote.NetworkService
import com.coal.profileapp.utlities.globals.NetworkConnectivity
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel(
    val compositeDesposible : CompositeDisposable?,
    val connectivity : NetworkConnectivity,
    val networkService : NetworkService
): ViewModel() {

    override fun onCleared() {
        compositeDesposible!!.dispose()
        super.onCleared()
    }

    protected fun checkInternetConnection(): Boolean = connectivity.isNetworkConnected()
    abstract fun onCreate()

}