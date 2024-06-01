package com.example.hms_projekit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hms_projekit.model.Event

@Dao
interface EventDao{
    @Query("SELECT * FROM events")
    suspend fun getAll(): List<Event>

    @Insert
    suspend fun insertAll(list: List<Event>)
}