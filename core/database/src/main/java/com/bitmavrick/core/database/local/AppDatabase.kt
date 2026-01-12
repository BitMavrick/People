package com.bitmavrick.core.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bitmavrick.core.database.local.dao.PeopleDao
import com.bitmavrick.core.database.local.entity.PeopleEntity
import com.bitmavrick.core.database.local.converters.GenderConverter

@Database(entities = [PeopleEntity::class], version = 1, exportSchema = false)
@TypeConverters(GenderConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun peopleDao(): PeopleDao
}