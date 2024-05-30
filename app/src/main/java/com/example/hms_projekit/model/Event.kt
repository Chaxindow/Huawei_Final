package com.example.hms_projekit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class EventResponse(
    val date: String,
    val wikipedia: String,
    val events: List<Event>
)

data class Event(
    val year: String,
    val description: String,
    val wikipedia: List<WikipediaEntry>
)

@Parcelize
data class WikipediaEntry(
    val title: String,
    val wikipedia: String
): Parcelable
