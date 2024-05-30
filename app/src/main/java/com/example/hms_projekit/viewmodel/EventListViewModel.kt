package com.example.hms_projekit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hms_projekit.model.Event
import com.example.hms_projekit.model.EventResponse
import com.example.hms_projekit.service.EventAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class EventListViewModel : ViewModel() {

    private val calendar = Calendar.getInstance()
    private val  day = calendar.get(Calendar.DAY_OF_MONTH).toString()
    private val  month = (calendar.get(Calendar.MONTH) + 1).toString()

    val event = MutableLiveData<List<Event>>()
    val eventLoad = MutableLiveData<Boolean>()
    val eventError = MutableLiveData<Boolean>()

    private val eventApiService = EventAPIService()

    fun fetchEvents() {

        eventLoad.value = false

        eventApiService.getData(month, day).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                eventLoad.value = false
                if (response.isSuccessful) {
                    event.value = response.body()?.events // Düzeltme burada
                    eventError.value = false
                } else {
                    eventError.value = true
                    Log.e("MainViewModel", "Error response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                eventLoad.value = false
                eventError.value = true
                Log.d("MainViewModel", "fetchEvents function onFailure", t)
            }
        })
    }
}