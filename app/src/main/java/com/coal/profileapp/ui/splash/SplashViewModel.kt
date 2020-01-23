package com.coal.profileapp.ui.splash

import androidx.lifecycle.MutableLiveData
import com.coal.profileapp.dataModel.local.DatabaseService
import com.coal.profileapp.dataModel.remote.NetworkService
import com.coal.profileapp.dataModel.repo.UserRepo
import com.coal.profileapp.ui.baseclasses.BaseViewModel
import com.coal.profileapp.utlities.globals.NetworkConnectivity
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(compositeDesposible : CompositeDisposable,
                      connectivity : NetworkConnectivity,
                      networkService : NetworkService,
                      databaseService: DatabaseService,
                      userRepo: UserRepo)
    : BaseViewModel(compositeDesposible,connectivity,networkService) {


    val launchMain: MutableLiveData<String> = MutableLiveData()


    override fun onCreate() {
            launchMain.postValue("launchMain")
    }
}