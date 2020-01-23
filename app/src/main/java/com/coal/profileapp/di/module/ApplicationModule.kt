package com.coal.profileapp.di.module


import androidx.room.Room
import com.coal.profileapp.ProfileApplicationClass
import com.coal.profileapp.dataModel.local.DatabaseService
import com.coal.profileapp.dataModel.remote.NetworkClient
import com.coal.profileapp.dataModel.remote.NetworkService
import com.coal.profileapp.utlities.globals.NetworkConnectivity
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class ApplicationModule(var applicationClass: ProfileApplicationClass) {

    @Provides
    @Singleton
    fun provideApplication() = applicationClass

    @Provides
    @Singleton
    fun provideNetworkConnectivity() = NetworkConnectivity(applicationClass)

    @Provides
    fun provideNetWorkService(): NetworkService =
        NetworkClient.create(
            "https://randomuser.me/api/",
            applicationClass.cacheDir,
            10 * 1024 * 1024 // 10MB
     )

    @Provides
    fun provideDatabaseService(): DatabaseService = Room.databaseBuilder(
        applicationClass,
        DatabaseService::class.java,
        "profile_data_base")
        .build()

    @Provides
    fun provideCompositeDesposible() = CompositeDisposable()

}
