package com.chelo.smartstock.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.chelo.smartstock.data.entities.ProductEntity
import com.chelo.smartstock.data.entities.ProductWithBranch
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    suspend fun insertProduct(productEntity: ProductEntity)

    @Delete
    suspend fun deleteProduct(productEntity: ProductEntity)

    @Update
    suspend fun updateProduct(productEntity: ProductEntity)


    @Transaction
    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductWithBranch>>


    @Query("SELECT * FROM products WHERE productId = :productId")
    suspend fun getProductById(productId: Long): ProductEntity

    @Transaction
    @Query("SELECT * FROM products WHERE branchFkId = :branchId")
    fun getProductsByBranch(branchId: Long): Flow<List<ProductWithBranch>>

    @Query("SELECT branchName FROM branches WHERE branchId = :branchId")
    suspend fun getBranchName(branchId: Long): String


}