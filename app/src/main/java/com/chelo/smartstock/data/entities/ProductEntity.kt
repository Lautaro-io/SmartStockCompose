package com.chelo.smartstock.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "products",
    foreignKeys = [
        ForeignKey(
            BranchEntity::class,
            parentColumns = ["branchId"],
            childColumns = ["branchFkId"],
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index("branchFkId")]
)

data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val productId: Long,
    val nameProduct: String,
    val count: Int,
    val expireDate: String,
    val codeBar: String,
    var image : String,
    val branchFkId: Long,
)