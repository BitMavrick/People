package com.bitmavrick.core.database.local.mapper

import com.bitmavrick.core.database.local.entity.PeopleEntity
import com.bitmavrick.core.model.People

internal fun PeopleEntity.asExternalModel() = People(
    id = id,
    name = name,
    age = age,
    gender = gender,
    orderIndex = orderIndex
)

internal fun People.asEntity() = PeopleEntity(
    id = id,
    name = name,
    age = age,
    gender = gender,
    orderIndex = orderIndex
)
