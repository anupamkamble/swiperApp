package com.coal.profileapp.dataModel.repo

import com.coal.profileapp.dataModel.model.User
import com.coal.profileapp.dataModel.remote.NetworkService
import com.coal.profileapp.utlities.globals.NetworkConnectivity
import io.reactivex.Single
import javax.inject.Inject


class UserRepo @Inject constructor(val networkService: NetworkService, val networkConnectivity: NetworkConnectivity) {

        fun  getProfile(): Single<User>{
            return  networkService.getProfile().map { it.results.get(0).user }
    }
}