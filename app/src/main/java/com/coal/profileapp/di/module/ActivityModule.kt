package com.coal.profileapp.di.module

import androidx.lifecycle.ViewModelProviders
import com.coal.profileapp.dataModel.local.DatabaseService
import com.coal.profileapp.dataModel.remote.NetworkService
import com.coal.profileapp.dataModel.repo.UserRepo
import com.coal.profileapp.ui.baseclasses.BaseActivity
import com.coal.profileapp.ui.home.HomeViewModel
import com.coal.profileapp.ui.splash.SplashViewModel
import com.coal.profileapp.utlities.globals.NetworkConnectivity
import com.coal.profileapp.utlities.globals.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideSplashViewModel(
        compositeDesposible : CompositeDisposable,
        connectivity : NetworkConnectivity,
        networkService : NetworkService,
        databaseService: DatabaseService,
        userRepo: UserRepo

    ): SplashViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(compositeDesposible, connectivity, networkService, databaseService ,userRepo)
            //this lambda creates and return SplashViewModel
        }).get(SplashViewModel::class.java)


    @Provides
    fun provideHomeViewModel(
        compositeDesposible : CompositeDisposable,
        connectivity : NetworkConnectivity,
        networkService : NetworkService,
        databaseService: DatabaseService,
        userRepo: UserRepo

    ): HomeViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(compositeDesposible, connectivity, networkService, databaseService ,userRepo)
        }).get(HomeViewModel::class.java)





}