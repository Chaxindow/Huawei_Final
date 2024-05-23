package com.example.hms_projekit.service

import com.example.hms_projekit.model.Event
import com.example.hms_projekit.model.EventResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EventAPI {

    @GET("{month}/{day}/events.json")
    fun getEvents(@Path("month") month: String, @Path("day") day: String): Call<EventResponse>

}