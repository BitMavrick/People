package com.bitmavrick.core.model

import java.math.BigInteger

data class People(
    val id: BigInteger,
    val name: String,
    val age: Int,
    val gender: GenderType,
    val orderIndex: BigInteger
)