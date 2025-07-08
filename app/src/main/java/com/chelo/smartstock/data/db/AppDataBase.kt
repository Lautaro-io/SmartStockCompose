package com.chelo.smartstock.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chelo.smartstock.data.daos.BranchDao
import com.chelo.smartstock.data.daos.ProductDao
import com.chelo.smartstock.data.daos.UserDao
import com.chelo.smartstock.data.entities.BranchEntity
import com.chelo.smartstock.data.entities.ProductEntity
import com.chelo.smartstock.data.entities.UserEntity


@Database(
    entities = [
        BranchEntity::class,
        ProductEntity::class,
        UserEntity::class
    ],
    version = 3
)

abstract class AppDataBase : RoomDatabase() {


    abstract fun userDao(): UserDao

    abstract fun branchDao(): BranchDao

    abstract fun productDao(): ProductDao


}