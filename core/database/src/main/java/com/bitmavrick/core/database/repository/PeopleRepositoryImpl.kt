package com.bitmavrick.core.database.repository

import com.bitmavrick.core.database.domain.repository.PeopleRepository
import com.bitmavrick.core.database.local.dao.PeopleDao
import com.bitmavrick.core.database.local.entity.PeopleEntity
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
        dao.insertPeople(
            PeopleEntity(
                id = people.id,
                name = people.name,
                age = people.age,
                gender = people.gender,
                orderIndex = people.orderIndex
            )
        )
    }

    override suspend fun updatePeople(people: People) {
        dao.updatePeople(
            PeopleEntity(
                id = people.id,
                name = people.name,
                age = people.age,
                gender = people.gender,
                orderIndex = people.orderIndex
            )
        )
    }

    override suspend fun deletePeople(people: People) {
        dao.deletePeople(
            PeopleEntity(
                id = people.id,
                name = people.name,
                age = people.age,
                gender = people.gender,
                orderIndex = people.orderIndex
            )
        )
    }

    override suspend fun reorderPeople(people: List<People>) {
        val reorderedPeople = people.mapIndexed { index, people ->
            PeopleEntity(
                id = people.id,
                name = people.name,
                age = people.age,
                gender = people.gender,
                orderIndex = index.toLong()
            )
        }

        dao.reorderPeople(reorderedPeople)
    }
}