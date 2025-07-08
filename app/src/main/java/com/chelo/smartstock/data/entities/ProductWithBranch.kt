package com.chelo.smartstock.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithBranch(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "branchFkId",
        entityColumn = "branchId"
    )
    val branch: BranchEntity,
)
