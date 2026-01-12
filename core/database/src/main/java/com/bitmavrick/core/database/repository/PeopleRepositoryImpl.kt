package com.bitmavrick.core.database.repository

import com.bitmavrick.core.database.domain.repository.PeopleRepository
import com.bitmavrick.core.database.local.dao.PeopleDao
import com.bitmavrick.core.model.People
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val dao: PeopleDao
) : PeopleRepository {
    override fun getAllPeople(): Flow<List<People>> {
        return dao.getAllPeople().map { list ->
            list.map {
                People(
                    id = it.id,
                    name = it.name,
                    age = it.age,
                    gender = it.gender,
                    orderIndex = it.orderIndex
                )
            }
        }
    }

    override suspend fun insertPeople(people: People) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePeople(people: People) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePeople(people: People) {
        TODO("Not yet implemented")
    }

    override suspend fun reorderPeople(people: List<People>) {
        TODO("Not yet implemented")
    }
}