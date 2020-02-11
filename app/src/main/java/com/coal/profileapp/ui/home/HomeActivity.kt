package com.coal.profileapp.ui.home

import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import com.coal.profileapp.R
import com.coal.profileapp.dataModel.local.entity.UserEntity
import com.coal.profileapp.dataModel.model.User
import com.coal.profileapp.di.component.ActivityComponent
import com.coal.profileapp.ui.baseclasses.BaseActivity
import com.coal.profileapp.utlities.globals.AppLogger
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity<HomeViewModel>(), CardStackListener {

    companion object{
        var  TAG ="HomeActivity"
    }
    lateinit var newSwipableCardView : CardStackView
    override fun provideResourceId()= R.layout.activity_home
    var swipedRightPosition = -1
    var userList  = ArrayList<User>()
    var offlineUsers  = ArrayList<User>()

    override fun setupView(savedInstanceState: Bundle?) {

        newSwipableCardView = newCardView
        newSwipableCardView.layoutManager = CardStackLayoutManager(this,this)
        (newSwipableCardView.layoutManager as CardStackLayoutManager).setDirections(Direction.HORIZONTAL)

        if (isNetWorkConnected()){
            progress.visibility = VISIBLE
            viewModel.getProfiles(false)
        }else{
            progress.visibility = VISIBLE
            Toast.makeText(this,getString(R.string.fetching_fav_profile),Toast.LENGTH_SHORT).show()
            viewModel.getAllOfflineUsers(false)
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.profileMutableLiveData.observe(this, Observer {
            progress.visibility = VISIBLE
            if(it == null){
                if(!isNetWorkConnected()){
                    Toast.makeText(this,getString(R.string.network_offline_msg),Toast.LENGTH_SHORT).show()
                    viewModel.getAllOfflineUsers(false) //handleCall
                }
            }else{
                AppLogger.e(TAG,"setupObservers")
                if(offlineUsers.size>0){
                    offlineUsers.clear()
                    newSwipableCardView.adapter?.notifyDataSetChanged()
                }
                if(userList.size == 0){
                    userList.add(it)
                    newSwipableCardView.adapter = SwipableAdapter(userList,this@HomeActivity)
                }else{
                    userList.add(it)
                    newSwipableCardView.adapter?.notifyItemInserted(userList.size-1)
                }
            }
            progress.visibility = View.GONE
        })


        viewModel.dbOpertaion.observe(this, Observer {
            progress.visibility = View.GONE
        })

        viewModel.offlineLiveData.observe(this, Observer {
            progress.visibility = View.GONE
            if(userList.size>0) {
                userList.clear()
                newSwipableCardView.adapter?.notifyDataSetChanged()
            }
            offlineUsers.addAll(it)
            newSwipableCardView.adapter = SwipableAdapter(offlineUsers,this@HomeActivity)

        })

        viewModel.notifyDataChanged.observe(this, Observer {
            newSwipableCardView.adapter?.notifyDataSetChanged()
        })
    }

    override fun onNetWorkChange() {
        super.onNetWorkChange()
        progress.visibility = VISIBLE
        swipedRightPosition = 0
        if(isNetWorkConnected()){
            Toast.makeText(application, getString(R.string.network_online_msg),Toast.LENGTH_SHORT).show()
            viewModel.getProfiles(true)
        }else{
            Toast.makeText(application, getString(R.string.network_offline_msg),Toast.LENGTH_SHORT).show()
            viewModel.getAllOfflineUsers(true)
        }
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }


    override fun onCardDisappeared(view: View?, position: Int) {
        AppLogger.e(TAG,"onCardDisappeared")
        swipedRightPosition = position
        if(position == offlineUsers.size && !isNetWorkConnected()){
            newSwipableCardView.scrollToPosition(0)
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardSwiped(direction: Direction?) {
        progress.visibility = VISIBLE
        if(direction==Direction.Right && isNetWorkConnected() && userList.size>0){
            var user = userList.get(swipedRightPosition)

            val userEntity = UserEntity(
                gender = user.gender,
                title = user.name.title,
                first = user.name.first,
                last = user.name.last,
                street = user.location.street,
                city = user.location.city,
                state = user.location.state,
                zip = user.location.zip,
                email = user.email,
                username = "",
                dob =user.dob,
                phone = user.phone,
                cell = user.phone,
                imageUrl =  user.picture)

            viewModel.insertUser(userEntity)
        }
        viewModel.getProfiles(false)
    }

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardRewound() {}

}
