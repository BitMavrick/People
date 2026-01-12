package com.bitmavrick.feature.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.bitmavrick.core.model.GenderType
import com.bitmavrick.core.model.People

@Composable
fun PeopleCard(
    people: People
) {
    // * Code from here
}

@PreviewLightDark
@Composable
private fun PeoplePreview(){
    val aPeople = People(
        name = "John Doe",
        age = 30,
        gender = GenderType.MALE,
        orderIndex = 0
    )

    PeopleCard(
        people = aPeople
    )
}