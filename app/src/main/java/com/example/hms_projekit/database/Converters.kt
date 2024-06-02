package com.example.hms_projekit.database

import androidx.room.TypeConverter
import com.example.hms_projekit.model.WikipediaEntry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromWikipediaEntryList(value: List<WikipediaEntry>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<WikipediaEntry>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toWikipediaEntryList(value: String): List<WikipediaEntry>? {
        val gson = Gson()
        val type = object : TypeToken<List<WikipediaEntry>>() {}.type
        return gson.fromJson(value, type)
    }
}
