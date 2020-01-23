package com.coal.profileapp.di.component

import com.coal.profileapp.ProfileApplicationClass
import com.coal.profileapp.dataModel.local.DatabaseService
import com.coal.profileapp.dataModel.remote.NetworkService
import com.coal.profileapp.dataModel.repo.UserRepo
import com.coal.profileapp.di.module.ApplicationModule
import com.coal.profileapp.utlities.globals.NetworkConnectivity
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: ProfileApplicationClass)

    fun getNetworkConnectivity() : NetworkConnectivity
    fun getNetWorkService(): NetworkService
    fun getCompositeDesposible() : CompositeDisposable
    fun getUserRepo(): UserRepo
    fun getDatabaseService(): DatabaseService

}