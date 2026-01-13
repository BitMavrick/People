package com.bitmavrick.feature.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bitmavrick.core.model.GenderType
import com.bitmavrick.core.model.People

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteDialog(
    people: People,
    onDismiss: () -> Unit,
    onConfirm: (People) -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = null
            )
        },
        title = {
            Text("Delete People")
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(people)
                }
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        },
        text = {
            Text("Are you sure you want to delete ${people.name}?")
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun DeleteDialogPreview(){
    val aPeople = People(
        name = "John Doe",
        age = 30,
        gender = GenderType.MALE,
        orderIndex = 0
    )

    DeleteDialog(
        people = aPeople,
        onDismiss = {},
        onConfirm = {}
    )
}