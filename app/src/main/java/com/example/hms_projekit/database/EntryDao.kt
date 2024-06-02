package com.example.hms_projekit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hms_projekit.model.WikipediaEntry

@Dao
interface EntryDao {
    @Query("SELECT * FROM entries WHERE eventId = :eventId")
    suspend fun getEntriesForEvent(eventId: Int): List<WikipediaEntry>

    @Insert
    suspend fun insertAll(entries: List<WikipediaEntry>)
}