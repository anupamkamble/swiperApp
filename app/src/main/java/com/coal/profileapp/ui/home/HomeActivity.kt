package com.coal.profileapp.ui.home

import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import com.coal.profileapp.R
import com.coal.profileapp.dataModel.model.User
import com.coal.profileapp.di.component.ActivityComponent
import com.coal.profileapp.ui.baseclasses.BaseActivity
import com.coal.profileapp.utlities.globals.AppLogger
import com.coal.profileapp.utlities.globals.CardClass
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity<HomeViewModel>(){

    companion object{
        var  TAG ="HomeActivity"
    }
    private lateinit var mSwipeView : SwipePlaceHolderView
    override fun provideResourceId()= R.layout.activity_home

    override fun setupView(savedInstanceState: Bundle?) {

        mSwipeView = swipeView.getBuilder<SwipePlaceHolderView, SwipeViewBuilder<SwipePlaceHolderView>>()
            .setDisplayViewCount(3)
            .setSwipeDecor(
                SwipeDecor()
                    .setPaddingTop(20)
                    .setRelativeScale(0.01f)
                    .setSwipeInMsgLayoutId(R.layout.card_swipe_in_layout)
                    .setSwipeOutMsgLayoutId(R.layout.card_swipe_out_layout)).swipePlaceHolderView

        if (isNetWorkConnected()){
            progress.visibility = VISIBLE
            viewModel.getProfiles()
        }else{
            progress.visibility = VISIBLE
            Toast.makeText(this,getString(R.string.fetching_fav_profile),Toast.LENGTH_SHORT).show()
            viewModel.getAllOfflineUsers()
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.profileMutableLiveData.observe(this, Observer {

            if(it == null){
                if(mSwipeView.childCount ==1){
                    Toast.makeText(this,getString(R.string.network_offline_msg),Toast.LENGTH_SHORT).show()
                    viewModel.getAllOfflineUsers() //handleCall
                }
            }else{
                mSwipeView.addView(CardClass(applicationContext, it as User, mSwipeView, viewModel.swipeLiveData , viewModel.swipeRightLiveData))
                AppLogger.e(TAG,"setupObservers")
            }

            progress.visibility = View.GONE
        })

       viewModel.swipeLiveData.observe(this, Observer {

           progress.visibility = VISIBLE

           if(it.equals("in")){
               AppLogger.e(TAG,"swiping in getting profile")
               viewModel.getProfiles()

           }else if(it.equals("out")){
               AppLogger.e(TAG,"swiping out getting profile")
               viewModel.getProfiles()
           }
       })

        viewModel.swipeRightLiveData.observe(this, Observer {
            if(isNetWorkConnected()){
                progress.visibility = VISIBLE
                viewModel.insertUser(it)
            }
        })

        viewModel.dbOpertaion.observe(this, Observer {
            progress.visibility = View.GONE
        })

        viewModel.offlineLiveData.observe(this, Observer {
            for (user in it.iterator())
                mSwipeView.addView(CardClass(applicationContext, user, mSwipeView, viewModel.swipeLiveData , viewModel.swipeRightLiveData))
        })
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun onNetWorkChange() {
        super.onNetWorkChange()
        if(isNetWorkConnected()){
            Toast.makeText(application, getString(R.string.network_online_msg),Toast.LENGTH_SHORT).show()
            viewModel.getProfiles()
        }else{
            Toast.makeText(application, getString(R.string.network_offline_msg),Toast.LENGTH_SHORT).show()
            viewModel.getAllOfflineUsers()
        }
    }
}
