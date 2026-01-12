package com.bitmavrick.feature.home

import androidx.lifecycle.ViewModel
import com.bitmavrick.core.database.domain.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository
) : ViewModel() {
    // * Code form here
}