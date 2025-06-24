package com.chelo.smartstock.di

import android.content.Context
import androidx.room.Room
import com.chelo.smartstock.data.daos.BranchDao
import com.chelo.smartstock.data.daos.ProductDao
import com.chelo.smartstock.data.daos.UserDao
import com.chelo.smartstock.data.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)


object Module {


    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "smartstockdb"
        ).fallbackToDestructiveMigration().build()
    }


    @Provides
    fun provideUserDao(db : AppDataBase): UserDao {
        return db.userDao()
    }

    @Provides
    fun provideBranchDao(db : AppDataBase): BranchDao {
        return db.branchDao()
    }

    @Provides
    fun provideProductDao(db : AppDataBase): ProductDao {
        return db.productDao()
    }
}