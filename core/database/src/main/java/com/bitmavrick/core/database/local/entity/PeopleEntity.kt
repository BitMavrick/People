package com.bitmavrick.core.database.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bitmavrick.core.model.GenderType
import java.math.BigInteger

@Entity(tableName = "People")
data class PeopleEntity (
    @PrimaryKey(autoGenerate = true)
    val id: BigInteger,
    val name: String,
    val age: Int,
    val gender: GenderType,
    val orderIndex: BigInteger
)