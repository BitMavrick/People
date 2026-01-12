package com.bitmavrick.core.model

import java.math.BigInteger

data class People(
    val id: Long = 0,
    val name: String,
    val age: Int,
    val gender: GenderType,
    val orderIndex: Long
)