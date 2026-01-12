package com.bitmavrick.feature.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.bitmavrick.feature.home.HomeUiEvent
import com.bitmavrick.feature.home.HomeUiState

@Composable
fun HomeContent(
    homeUiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            if(homeUiState.isLoading){
                Text("Loading ...")
            }else{
                Text("not loading")
            }

        }
    }
}

@PreviewLightDark
@Composable
private fun HomeContentPreview(){
    HomeContent(
        homeUiState = HomeUiState(),
        onEvent = {}
    )
}