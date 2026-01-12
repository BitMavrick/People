package com.bitmavrick.core.database.repository

import com.bitmavrick.core.database.domain.repository.PeopleRepository
import com.bitmavrick.core.database.local.dao.PeopleDao
import com.bitmavrick.core.database.local.mapper.asEntity
import com.bitmavrick.core.database.local.mapper.asExternalModel
import com.bitmavrick.core.model.People
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PeopleRepositoryImpl @Inject constructor(
    private val dao: PeopleDao
) : PeopleRepository {
    override fun getAllPeople(): Flow<List<People>> {
        return dao.getAllPeople().map { list ->
            list.map { it.asExternalModel() }
        }
    }

    override suspend fun insertPeople(people: People) {
        dao.insertPeople(people.asEntity())
    }

    override suspend fun updatePeople(people: People) {
        dao.updatePeople(people.asEntity())
    }

    override suspend fun deletePeople(people: People) {
        dao.deletePeople(people.asEntity())
    }

    override suspend fun reorderPeople(people: List<People>) {
        val reorderedEntities = people.mapIndexed { index, person ->
            person.asEntity().copy(orderIndex = index.toLong())
        }
        dao.reorderPeople(reorderedEntities)
    }
}