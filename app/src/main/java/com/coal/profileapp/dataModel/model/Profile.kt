package com.coal.profileapp.dataModel.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Profile(
    @SerializedName("name")
    @Expose
    var  name:String,

    @SerializedName("url")
    @Expose
    var  imageUrl:String,

    @SerializedName("age")
    @Expose
    var  age:Int,

    @SerializedName("location")
    @Expose
    var  location:String,

    @SerializedName("phone")
    @Expose
    var  phone:String,

    @SerializedName("dob")
    @Expose
    var  dob:Long
)