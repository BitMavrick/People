package com.bitmavrick.core.database.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bitmavrick.core.model.GenderType

@Entity(tableName = "People")
internal data class PeopleEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val age: Int,
    val gender: GenderType,
    val orderIndex: Long
)