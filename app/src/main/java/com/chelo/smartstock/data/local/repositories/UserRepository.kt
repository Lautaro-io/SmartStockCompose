package com.chelo.smartstock.data.local.repositories

import com.chelo.smartstock.data.daos.UserDao
import com.chelo.smartstock.data.entities.UserEntity
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao : UserDao) {

    suspend fun insertUser(userEntity: UserEntity) : Long = userDao.insertUser(userEntity)

    suspend fun deleteUser(userEntity: UserEntity) = userDao.deleteUser(userEntity)

    suspend fun updateUser(userEntity: UserEntity) = userDao.updateUser(userEntity)

}
