package com.bitmavrick.feature.home

import com.bitmavrick.core.model.People

sealed class HomeUiEvent {
    data class AddNewPeople(val people: People) : HomeUiEvent()
    data class UpdatePeople(val people: People) : HomeUiEvent()
    data class DeletePeople(val people: People) : HomeUiEvent()
    data class ReorderPeople(val people: List<People>) : HomeUiEvent()
    object UserMessageShown : HomeUiEvent()
}