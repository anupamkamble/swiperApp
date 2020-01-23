package com.coal.profileapp.dataModel.remote.response

import com.coal.profileapp.dataModel.model.Results
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class  ProfileResponse(
    @Expose
    @SerializedName("results")
    var results : List<Results>
)