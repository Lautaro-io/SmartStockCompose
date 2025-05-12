package com.chelo.smartstock.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val userId: Long = 0,
    val name: String,
    val surname: String,
)
