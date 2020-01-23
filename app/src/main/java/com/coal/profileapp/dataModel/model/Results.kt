package com.coal.profileapp.dataModel.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("user")
    @Expose
    var  user:User
)