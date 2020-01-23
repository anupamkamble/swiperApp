package com.coal.profileapp.dataModel.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.coal.profileapp.dataModel.local.entity.UserEntity
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface UserDao {
    @Insert
    fun insert(userEntity: UserEntity): Single<Long>

    @Query("SELECT * FROM users")
    fun getAllUsers(): Single<List<UserEntity>>

    @Query("SELECT * FROM users")
    fun getAllUsersById(): Observable<List<UserEntity>>

}