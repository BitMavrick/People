package com.bitmavrick.feature.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark

@Composable
fun AddPeopleDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = null
            )
        },
        title = { Text("Add People") },
        text = {

        },
        onDismissRequest = onDismiss,
        confirmButton = {},
    )
}

@PreviewLightDark
@Composable
private fun AddPeopleDialogPreview(){
    AddPeopleDialog(
        onDismiss = {}
    )
}