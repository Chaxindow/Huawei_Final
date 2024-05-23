package com.example.hms_projekit.service

import com.example.hms_projekit.model.Event
import com.example.hms_projekit.model.EventResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventAPIService {

    private val BASE_URL = "https://byabbe.se/on-this-day/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EventAPI::class.java)

    fun getData(month: String, day: String) : Call<EventResponse> {
        return api.getEvents(month,day)
    }

}