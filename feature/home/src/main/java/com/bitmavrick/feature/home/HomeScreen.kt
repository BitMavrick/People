package com.bitmavrick.feature.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit
) {
    Text(
        "Hello World"
    )
}