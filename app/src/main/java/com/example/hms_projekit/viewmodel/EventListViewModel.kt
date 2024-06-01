package com.example.hms_projekit.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hms_projekit.database.EventDao
import com.example.hms_projekit.database.EventDatabase
import com.example.hms_projekit.model.Event
import com.example.hms_projekit.model.EventResponse
import com.example.hms_projekit.service.EventAPIService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class EventListViewModel(application: Application) : AndroidViewModel(application) {

    private val calendar = Calendar.getInstance()
    private val day = calendar.get(Calendar.DAY_OF_MONTH).toString()
    private val month = (calendar.get(Calendar.MONTH) + 1).toString()

    val event = MutableLiveData<List<Event>>()
    val eventLoad = MutableLiveData<Boolean>(false)
    val eventError = MutableLiveData<Boolean>(false)

    private val eventApiService = EventAPIService()

    private var eventDatabase:EventDatabase?=null
    private var eventDao:EventDao?= null

    init{
        eventDatabase=EventDatabase.getInstance(application)
        eventDao=eventDatabase?.eventDao()

    }

    fun fetchEvents() {

        eventLoad.value = true

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

    fun insertAll(list: List<Event>) = viewModelScope.launch{
        eventDao?.insertAll(list)
    }
}