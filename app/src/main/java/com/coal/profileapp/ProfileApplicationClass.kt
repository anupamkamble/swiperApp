package com.coal.profileapp

import android.app.Application
import com.coal.profileapp.di.component.ApplicationComponent
import com.coal.profileapp.di.component.DaggerApplicationComponent
import com.coal.profileapp.di.module.ApplicationModule

class ProfileApplicationClass : Application(){

    lateinit var appComponent : ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependency()
    }

    fun injectDependency(){

        appComponent = DaggerApplicationComponent
                       .builder()
                       .applicationModule(ApplicationModule(this))
                       .build()

        appComponent.inject(this)
    }

}