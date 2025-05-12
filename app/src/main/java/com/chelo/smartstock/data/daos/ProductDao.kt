package com.chelo.smartstock.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.chelo.smartstock.data.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    suspend fun insertProduct(productEntity: ProductEntity)

    @Delete
    suspend fun deleteProduct (productEntity: ProductEntity)

    @Update
    suspend fun updateProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE branchFkId = :branchId")
    fun getBranchProducts(branchId: Long):Flow<List<ProductEntity>>


}