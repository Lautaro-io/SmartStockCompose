package com.chelo.smartstock.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "branches",
    foreignKeys = [
        ForeignKey(
            UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["userIdFk"],
            onDelete = ForeignKey.CASCADE

        )
    ],
    indices = [Index("userIdFk")]
)


data class BranchEntity(
    @PrimaryKey(autoGenerate = true) val branchId: Long = 0 ,
    val branchName: String,
    val userIdFk: Long,

    )