package com.bitmavrick.feature.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import com.bitmavrick.core.model.GenderType
import com.bitmavrick.core.model.People
import com.bitmavrick.feature.home.HomeUiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPeopleDialog(
    onEvent: (HomeUiEvent) -> Unit,
    onDismiss: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val genderSelection = remember { mutableStateOf(GenderType.MALE) }
    val expanded = remember { mutableStateOf(false) }

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
                    modifier = Modifier.fillMaxWidth(),
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = { Text("Name") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = age.value,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) {
                            age.value = it
                        }
                    },
                    label = { Text("Age") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                ExposedDropdownMenuBox(
                    expanded = expanded.value,
                    onExpandedChange = { expanded.value = it }
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable, true),
                        value = genderSelection.value.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Gender") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                        },
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }
                    ) {
                        GenderType.entries.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption.name) },
                                onClick = {
                                    genderSelection.value = selectionOption
                                    expanded.value = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }
                }
            }
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    val newPeople = People(
                        name = name.value,
                        age = age.value.toIntOrNull() ?: 0,
                        gender = genderSelection.value,
                        orderIndex = 0
                    )

                    onEvent(HomeUiEvent.AddNewPeople(newPeople))
                    onDismiss()
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
        onEvent = {},
        onDismiss = {}
    )
}