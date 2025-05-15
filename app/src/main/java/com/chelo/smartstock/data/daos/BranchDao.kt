package com.chelo.smartstock.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.chelo.smartstock.data.entities.BranchEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface BranchDao {

    @Insert
    suspend fun insertBranch(branch: BranchEntity)


    @Delete
    suspend fun deleteBranch(branch: BranchEntity)

    @Update
    suspend fun updateBranch(branchDao: BranchEntity)


    @Query("SELECT * FROM branches")
    fun getAllBranches(): Flow<List<BranchEntity>>
}