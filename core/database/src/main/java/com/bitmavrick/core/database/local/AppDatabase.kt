package com.bitmavrick.core.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bitmavrick.core.database.local.dao.PeopleDao
import com.bitmavrick.core.database.local.entity.PeopleEntity

@Database(entities = [PeopleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun peopleDao(): PeopleDao
}