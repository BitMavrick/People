package com.bitmavrick.core.database.local.converters

import androidx.room.TypeConverter
import com.bitmavrick.core.model.GenderType

class GenderConverter {

    @TypeConverter
    fun fromGenderType(gender: GenderType): String {
        return gender.name
    }

    @TypeConverter
    fun toGenderType(gender: String): GenderType {
        return try {
            GenderType.valueOf(gender)
        } catch (e: IllegalArgumentException) {
            GenderType.OTHER
        }
    }
}
