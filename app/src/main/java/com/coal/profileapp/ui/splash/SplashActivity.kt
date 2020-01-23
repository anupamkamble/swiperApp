package com.coal.profileapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.coal.profileapp.R
import com.coal.profileapp.di.component.ActivityComponent
import com.coal.profileapp.ui.baseclasses.BaseActivity
import com.coal.profileapp.ui.home.HomeActivity


class SplashActivity : BaseActivity<SplashViewModel>(){


    companion object{
        val TAG = "SplashActivity"
    }
    override fun provideResourceId()  = R.layout.acitivity_splash
    override fun setupView(savedInstanceState: Bundle?) {
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }


    override fun setupObservers() {
        super.setupObservers()
        viewModel.launchMain.observe(this, Observer {
            it.equals("launchMain").run {
                startActivity(Intent(applicationContext,HomeActivity::class.java))
                finish()
            }

        })
    }




}
