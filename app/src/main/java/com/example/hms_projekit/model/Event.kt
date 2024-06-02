package com.example.hms_projekit.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.hms_projekit.database.Converters
import kotlinx.android.parcel.Parcelize


data class EventResponse(
    val date: String,
    val wikipedia: String,
    val events: List<Event>
)

@Entity("events")
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name="year")
    val year: String,
    @ColumnInfo(name="description")
    val description: String,
    @ColumnInfo(name="wikipedia")
    @TypeConverters(Converters::class)
    val wikipedia: List<WikipediaEntry>
)

@Parcelize
data class WikipediaEntry(
    val title: String,
    val wikipedia: String
): Parcelable