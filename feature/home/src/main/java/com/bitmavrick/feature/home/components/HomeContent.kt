package com.bitmavrick.feature.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.bitmavrick.feature.home.HomeUiEvent
import com.bitmavrick.feature.home.HomeUiState

@Composable
fun HomeContent(
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit
) {
    if(uiState.isLoading && uiState.people.isEmpty()){ // * Loading state
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{
                Text("Finding peoples ...")
            }
        }
    } else if(!uiState.isLoading && uiState.people.isEmpty()){ // * Empty state
        // * Empty
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{
                Text("No crowed here!")
            }
        }
    } else {  // * Success state
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{
                Text("success")
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun HomeContentPreview(){
    HomeContent(
        uiState = HomeUiState(),
        onEvent = {}
    )
}