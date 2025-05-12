package com.chelo.smartstock.data.local.repositories

import com.chelo.smartstock.data.daos.BranchDao
import com.chelo.smartstock.data.entities.BranchEntity
import javax.inject.Inject

class BranchRepository @Inject constructor(private val branchDao: BranchDao) {

    suspend fun insertBranch(branchEntity: BranchEntity) = branchDao.insertBranch(branchEntity)

    suspend fun deleteBranch(branchEntity: BranchEntity) = branchDao.deleteBranch(branchEntity)

    suspend fun updateBranch(branchEntity: BranchEntity) = branchDao.updateBranch(branchEntity)
}