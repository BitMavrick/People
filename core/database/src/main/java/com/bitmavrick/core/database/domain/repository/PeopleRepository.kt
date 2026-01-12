package com.bitmavrick.core.database.domain.repository

import com.bitmavrick.core.model.People
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getAllPeople(): Flow<List<People>>
    suspend fun insertPeople(people: People)
    suspend fun updatePeople(people: People)
    suspend fun deletePeople(people: People)
    suspend fun reorderPeople(people: List<People>)
}