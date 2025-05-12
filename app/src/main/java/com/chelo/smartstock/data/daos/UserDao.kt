package com.chelo.smartstock.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.chelo.smartstock.data.entities.UserEntity

@Dao

interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity): Long

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)
}