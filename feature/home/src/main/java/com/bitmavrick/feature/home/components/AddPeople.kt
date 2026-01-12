package com.bitmavrick.feature.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

@Composable
fun AddPeopleDialog(
    onDismiss: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }

    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = null
            )
        },
        title = { Text("Add People") },
        text = {
            Column {
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = { Text("Name") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = age.value,
                    onValueChange = {
                        // 👇 Allow only digits
                        if (it.all { char -> char.isDigit() }) {
                            age.value = it
                        }
                    },
                    label = { Text("Age") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    age.value.toIntOrNull()
                }
            ) {
                Text("Save")
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun AddPeopleDialogPreview(){
    AddPeopleDialog(
        onDismiss = {}
    )
}