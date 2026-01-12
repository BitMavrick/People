package com.bitmavrick.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitmavrick.core.database.domain.repository.PeopleRepository
import com.bitmavrick.core.model.People
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    private val _userMessage = MutableStateFlow<String?>(null)

    val uiState: StateFlow<HomeUiState> = combine(
        peopleRepository.getAllPeople(),
        _isLoading,
        _userMessage
    ) { people, isLoading, userMessage ->
        HomeUiState(
            people = people,
            isLoading = isLoading,
            userMessage = userMessage
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState(isLoading = true)
    )

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.AddNewPeople -> addNewPeople(event.people)
            is HomeUiEvent.UpdatePeople -> updatePeople(event.people)
            is HomeUiEvent.DeletePeople -> deletePeople(event.people)
            is HomeUiEvent.ReorderPeople -> reorderPeople(event.people)
            is HomeUiEvent.Refresh -> refresh()
            is HomeUiEvent.UserMessageShown -> userMessageShown()
        }
    }

    private fun addNewPeople(people: People) {
        viewModelScope.launch {
            _isLoading.update { true }
            try {
                peopleRepository.insertPeople(people)
                _userMessage.update { "Person added successfully" }
            } catch (e: Exception) {
                _userMessage.update { "Error adding person: ${e.message}" }
            } finally {
                _isLoading.update { false }
            }
        }
    }

    private fun updatePeople(people: People) {
        viewModelScope.launch {
            _isLoading.update { true }
            try {
                peopleRepository.updatePeople(people)
                _userMessage.update { "Person updated successfully" }
            } catch (e: Exception) {
                _userMessage.update { "Error updating person: ${e.message}" }
            } finally {
                _isLoading.update { false }
            }
        }
    }

    private fun deletePeople(people: People) {
        viewModelScope.launch {
            _isLoading.update { true }
            try {
                peopleRepository.deletePeople(people)
                _userMessage.update { "Person deleted successfully" }
            } catch (e: Exception) {
                _userMessage.update { "Error deleting person: ${e.message}" }
            } finally {
                _isLoading.update { false }
            }
        }
    }

    private fun reorderPeople(people: List<People>) {
        viewModelScope.launch {
            _isLoading.update { true }
            try {
                peopleRepository.reorderPeople(people)
                _userMessage.update { "List reordered successfully" }
            } catch (e: Exception) {
                _userMessage.update { "Error reordering people: ${e.message}" }
            } finally {
                _isLoading.update { false }
            }
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            _isLoading.update { true }
            try {
                // Simulate a network refresh or check for updates
                delay(1000)
                _userMessage.update { "Refreshed successfully" }
            } catch (e: Exception) {
                _userMessage.update { "Error refreshing: ${e.message}" }
            } finally {
                _isLoading.update { false }
            }
        }
    }

    private fun userMessageShown() {
        _userMessage.update { null }
    }
}