package com.coal.profileapp.dataModel.remote

import com.coal.profileapp.dataModel.remote.response.ProfileResponse
import io.reactivex.Single
import retrofit2.http.GET


interface NetworkService {

    //"https://randomuser.me/api/0.4/?randomapi"
    @GET(Endpoints.GET_USER)
    fun getProfile(): Single<ProfileResponse>

}