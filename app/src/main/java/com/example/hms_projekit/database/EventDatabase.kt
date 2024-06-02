package com.example.hms_projekit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hms_projekit.model.Event

@Database(entities = [Event::class], version= 1)
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