package com.coal.profileapp.dataModel.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")

data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,

    @ColumnInfo(name = "gender")
    var gender : String,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "first")
    var first : String,

    @ColumnInfo(name = "last")
    var last : String,

    @ColumnInfo(name = "street")
    var street : String,

    @ColumnInfo(name = "city")
    var city : String,

    @ColumnInfo(name = "state")
    var state : String,

    @ColumnInfo(name = "zip")
    var zip : String,

    @ColumnInfo(name = "email")
    var email : String,

    @ColumnInfo(name = "username")
    var username : String,

    @ColumnInfo(name = "dob")
    var dob : Long,

    @ColumnInfo(name = "phone")
    var phone : String,

    @ColumnInfo(name = "cell")
    var cell : String,

    @ColumnInfo(name = "imageUrl")
    var imageUrl : String
)