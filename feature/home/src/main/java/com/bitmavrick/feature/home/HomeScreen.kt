package com.bitmavrick.feature.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.bitmavrick.feature.home.components.HomeContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(homeUiState.userMessage) {
        if (homeUiState.userMessage != null) {
            snackbarHostState.showSnackbar(homeUiState.userMessage)
            onEvent(HomeUiEvent.UserMessageShown)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("People")
                },
                actions = {
                    IconButton(
                        onClick = {
                            // * Code from here
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    // * Code from here
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                },
                text = {
                    Text(
                        "People"
                    )
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        PullToRefreshBox(
            modifier = Modifier.padding(it),
            isRefreshing = homeUiState.isLoading,
            onRefresh = { onEvent(HomeUiEvent.Refresh) }
        ) {
            HomeContent(
                uiState = homeUiState,
                onEvent = onEvent
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun HomeScreenPreview(){
    HomeScreen(
        homeUiState = HomeUiState(),
        onEvent = {}
    )
}