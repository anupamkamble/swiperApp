package com.coal.profileapp.dataModel.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("gender")
    @Expose
    val gender : String,

    @Expose
    @SerializedName("name")
    var name : Name,

    @Expose
    @SerializedName("location")
    var location : Location,

    @Expose
    @SerializedName("email")
    var email : String,


    @Expose
    @SerializedName("registered")
    var registered : String,


    @Expose
    @SerializedName("dob")
    var dob : Long,


    @Expose
    @SerializedName("phone")
    var phone : String,

    @Expose
    @SerializedName("cell")
    var cell : String,

    @Expose
    @SerializedName("picture")
    var picture : String,

    val isOffline : Boolean = false

){
    constructor() : this("",Name("","",""),Location("","","",""),"","",-1,"","","",false)

    data  class Name(
        @Expose
        @SerializedName("title")
        var title : String,
        @Expose
        @SerializedName("first")
        var first : String,

        @Expose
        @SerializedName("last")
        var last : String){

    }

    data class Location(

        @SerializedName("street")
        @Expose
        var  street:String,

        @SerializedName("city")
        @Expose
        var  city:String,

        @SerializedName("state")
        @Expose
        var  state:String,

        @SerializedName("zip")
        @Expose
        var  zip:String
    )




}