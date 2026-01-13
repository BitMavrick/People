package com.bitmavrick.feature.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.bitmavrick.feature.home.HomeUiState

@Composable
fun HomeContent(
    uiState: HomeUiState
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
    } else {
        val showDescriptionCard = rememberSaveable { mutableStateOf(false) }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)
        ) {
            items(uiState.people.size) {
                PeopleCard(
                    people = uiState.people[it],
                    onClickCard = {
                       showDescriptionCard.value = true
                    },
                    onDrag = { /*TODO*/ }
                )

                if(showDescriptionCard.value){
                    PersonDescriptionModal(
                        people = uiState.people[it],
                        onDismiss = {
                            showDescriptionCard.value = false
                        },
                        onEdit = {},
                        onDelete = {}
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun HomeContentPreview(){
    HomeContent(
        uiState = HomeUiState()
    )
}