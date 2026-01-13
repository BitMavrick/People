package com.bitmavrick.feature.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DragHandle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bitmavrick.core.model.GenderType
import com.bitmavrick.core.model.People

@Composable
fun PeopleCard(
    people: People,
    onClickCard: () -> Unit,
    onDrag: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )

            Column(
                modifier = Modifier.weight(1f).padding(8.dp)
            ) {
                Text(
                    text = "Name: ${people.name}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Gender: ${people.gender.toString().lowercase().replaceFirstChar { it.uppercase() }}",
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = "Age: ${people.age}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Outlined.DragHandle,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PeoplePreview(){
    val aPeople = People(
        name = "John Doe",
        age = 30,
        gender = GenderType.MALE,
        orderIndex = 0
    )

    PeopleCard(
        people = aPeople,
        onClickCard = {},
        onDrag = {}
    )
}