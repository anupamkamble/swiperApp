package com.coal.profileapp.ui.home

import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.coal.profileapp.R
import com.coal.profileapp.dataModel.local.DatabaseService
import com.coal.profileapp.dataModel.local.entity.UserEntity
import com.coal.profileapp.dataModel.model.User
import com.coal.profileapp.dataModel.remote.NetworkService
import com.coal.profileapp.dataModel.repo.UserRepo
import com.coal.profileapp.ui.baseclasses.BaseViewModel
import com.coal.profileapp.utlities.globals.AppLogger
import com.coal.profileapp.utlities.globals.NetworkConnectivity
import com.coal.profileapp.utlities.globals.ViewStates
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class HomeViewModel(compositeDesposible : CompositeDisposable?,
                     connectivity : NetworkConnectivity,
                     networkService : NetworkService,
                     private val dbService: DatabaseService,
                     private  val userRepo: UserRepo)
    : BaseViewModel(compositeDesposible,connectivity,networkService) {


    var profileMutableLiveData: MutableLiveData<User> = MutableLiveData()
    var offlineLiveData: MutableLiveData<List<User>> = MutableLiveData()
    var swipeRightLiveData: MutableLiveData<UserEntity> = MutableLiveData()
    val stateWithMessage: MutableLiveData<ViewStates<Int>> = MutableLiveData()
    var dbOpertaion: MutableLiveData<Boolean> = MutableLiveData()
    var swipeLiveData: MutableLiveData<String> = MutableLiveData()



    companion object {
        val TAG = "HomeViewModel"
    }
    override fun onCreate() {
    }

    override fun onCleared() {
        super.onCleared()
        if(compositeDesposible != null && !compositeDesposible.isDisposed){
            compositeDesposible.dispose()
            compositeDesposible.clear()
        }
    }

    fun insertUser(user: UserEntity){
        compositeDesposible!!.add(
            dbService.userDao()
                .insert(user)
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    AppLogger.e(TAG,"inserted $it")
                    stateWithMessage.postValue(ViewStates.success(R.string.success))
                },{
                    stateWithMessage.postValue(ViewStates.error(R.string.server_connection_error))
                })
        )
        dbOpertaion.postValue(true)
    }

    fun getAllOfflineUsers(){
        compositeDesposible!!.add(

            dbService.userDao()
                .getAllUsers()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    val list = ArrayList<User>()
                    for (user in it.iterator()){
                        val bmp = BitmapFactory.decodeByteArray(user.image, 0, user.image.size)
                        var convertedUser = User(
                            gender = user.gender,
                            name = User.Name(user.title,user.first,user.last),
                            location = User.Location(user.street,user.city,user.state,user.zip),
                            email = user.email,
                            registered = "",
                            dob = user.dob,
                            phone = user.phone,
                            cell = user.cell,
                            isOffline = true,
                            bitmap = bmp,
                            picture = ""
                        )

                        list.apply {
                            add(convertedUser)
                        }
                    }
                    offlineLiveData.postValue(list)
                    stateWithMessage.postValue(ViewStates.success(R.string.success))
                },
                    {
                        AppLogger.e(TAG,"exception came offline data $it")
                        stateWithMessage.postValue(ViewStates.error(R.string.server_connection_error))
                    })
        )


        compositeDesposible.add(

            dbService.userDao()
                .getAllUsersById()
                .map {
                    it.get(0).dob>0
                            return@map it.get(0)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {

                    },{

                    },{

                    },{

                    }
                )
        )
    }

    fun getProfiles() {

        if (connectivity.isNetworkConnected()){
            compositeDesposible!!.add(
                userRepo.getProfile()
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        AppLogger.e(TAG, it.toString())
                        profileMutableLiveData.postValue(it)
                        stateWithMessage.postValue(ViewStates.success(R.string.success))
                    },
                        {
                            AppLogger.e(TAG, "error:-onCreate")
                            stateWithMessage.postValue(ViewStates.error(R.string.server_connection_error))
                        }
                    )
            )
        }else{
            profileMutableLiveData.postValue(null)
            stateWithMessage.postValue(ViewStates.error(R.string.network_connection_error))
        }
    }
}