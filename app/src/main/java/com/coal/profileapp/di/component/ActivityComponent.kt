package com.coal.profileapp.di.component

import com.coal.profileapp.di.Scope.ActivityScope
import com.coal.profileapp.di.module.ActivityModule
import com.coal.profileapp.ui.home.HomeActivity
import com.coal.profileapp.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(acivity: SplashActivity)

    fun inject(acivity: HomeActivity)

}