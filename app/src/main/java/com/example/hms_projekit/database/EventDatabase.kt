package com.example.hms_projekit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hms_projekit.model.Event
import com.example.hms_projekit.model.WikipediaEntry

@Database(entities = [Event::class, WikipediaEntry::class], version= 1)
@TypeConverters(Converters::class)
abstract class EventDatabase : RoomDatabase(){
    abstract fun eventDao():EventDao
    abstract fun entryDao():EntryDao

    companion object {
        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getInstance(context: Context): EventDatabase {
            return INSTANCE?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "event-db"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}