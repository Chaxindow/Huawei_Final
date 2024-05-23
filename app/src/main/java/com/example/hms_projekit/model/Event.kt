package com.example.hms_projekit.model

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

data class WikipediaEntry(
    val title: String,
    val wikipedia: String
)
