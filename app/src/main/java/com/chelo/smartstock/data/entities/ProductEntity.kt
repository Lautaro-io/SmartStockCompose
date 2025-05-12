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
            parentColumns = ["branchId"], //Aca en parent se refiere a la columna de la otra tabla a la que queremos referenciar
            childColumns = ["branchFkId"], //Y por ende el Child  se refiere a la que le asignamos el valor o tenemos de referencia
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
    var image : String? = "https://cdn.iconscout.com/icon/premium/png-512-thumb/empty-1670040-1418099.png?f=webp&w=512",
    val branchFkId: Long,
)