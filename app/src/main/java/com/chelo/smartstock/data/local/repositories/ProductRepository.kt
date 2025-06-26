package com.chelo.smartstock.data.local.repositories

import com.chelo.smartstock.data.daos.ProductDao
import com.chelo.smartstock.data.entities.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDao: ProductDao) {

    suspend fun insertProduct(productEntity: ProductEntity) =
        productDao.insertProduct(productEntity)

    suspend fun deleteProduct(productEntity: ProductEntity) =
        productDao.deleteProduct(productEntity)

    suspend fun updateProduct(productEntity: ProductEntity) =
        productDao.updateProduct(productEntity)

    fun getAllProducts(): Flow<List<ProductEntity>> = productDao.getAllProducts()

    fun getBranchProducts(branchId: Long): Flow<List<ProductEntity>> =
        productDao.getBranchProducts(branchId)

    suspend  fun getProductById(productId: Long) = productDao.getProductById(productId)


}