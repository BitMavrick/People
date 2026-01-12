package com.bitmavrick.core.database.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.bitmavrick.core.database.local.entity.PeopleEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface PeopleDao {
    @Query("SELECT * FROM People ORDER BY orderIndex ASC")
    fun getAllPeople(): Flow<List<PeopleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(people: PeopleEntity)

    @Update
    suspend fun updatePeople(peopleEntity: PeopleEntity)

    @Delete
    suspend fun deletePeople(people: PeopleEntity)

    @Transaction
    suspend fun reorderPeople(people: List<PeopleEntity>) {
        people.forEachIndexed { index, peopleEntity ->
            updatePeople(peopleEntity.copy(orderIndex = index.toLong()))
        }
    }
}