package com.bitmavrick.feature.home

import com.bitmavrick.core.model.People

data class HomeUiState(
    val people: List<People> = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: String? = null
)