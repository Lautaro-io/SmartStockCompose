package com.chelo.smartstock.data.local.repositories

import com.chelo.smartstock.data.daos.ProductDao
import com.chelo.smartstock.data.entities.ProductEntity
import com.chelo.smartstock.data.entities.ProductWithBranch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDao: ProductDao) {

    suspend fun insertProduct(productEntity: ProductEntity) =
        productDao.insertProduct(productEntity)

    suspend fun deleteProduct(productEntity: ProductEntity) =
        productDao.deleteProduct(productEntity)

    suspend fun updateProduct(productEntity: ProductEntity) =
        productDao.updateProduct(productEntity)

    fun getAllProducts(): Flow<List<ProductWithBranch>> = productDao.getAllProducts()


    suspend fun getProductById(productId: Long) = productDao.getProductById(productId)

    suspend fun getProductsByBranch(branchId: Long) = productDao.getProductsByBranch(branchId)


    suspend fun getBranchName(id: Long): String = productDao.getBranchName(id)

}