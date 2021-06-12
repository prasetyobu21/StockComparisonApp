package com.pras.bareksatest.model.local.room

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converter {
    @TypeConverter
    fun fromList(value : Array<Double>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<Array<Double>>(value)
}
