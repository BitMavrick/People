package com.bitmavrick.feature.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.bitmavrick.core.model.People
import com.bitmavrick.feature.home.HomeUiEvent
import com.bitmavrick.feature.home.HomeUiState

@Composable
fun HomeContent(
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
) {
    if(uiState.isLoading && uiState.people.isEmpty()){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{
                Text("Finding peoples ...")
            }
        }
    } else if(!uiState.isLoading && uiState.people.isEmpty()){
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
        val showPersonDescription = rememberSaveable { mutableStateOf<People?>(null) }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)
        ) {
            items(
                items = uiState.people,
                key = { it.id }
            ) { person ->
                PeopleCard(
                    modifier = Modifier.animateItem(),
                    people = person,
                    onClickCard = {
                        showPersonDescription.value = person
                    },
                    onDrag = { /*TODO*/ }
                )
            }
        }

        showPersonDescription.value?.let { person ->
            PersonDescriptionModal(
                people = person,
                onEvent = onEvent,
                onDismiss = {
                    showPersonDescription.value = null
                }
            )
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