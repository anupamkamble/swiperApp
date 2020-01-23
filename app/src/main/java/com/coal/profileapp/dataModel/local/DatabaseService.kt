package com.coal.profileapp.dataModel.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coal.profileapp.dataModel.local.dao.UserDao
import com.coal.profileapp.dataModel.local.entity.UserEntity


@Database(
    entities = [UserEntity::class],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase(){
    abstract fun userDao():UserDao

}