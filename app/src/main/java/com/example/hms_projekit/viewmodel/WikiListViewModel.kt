package com.example.hms_projekit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hms_projekit.model.Event
import com.example.hms_projekit.model.EventResponse
import com.example.hms_projekit.model.WikipediaEntry
import com.example.hms_projekit.service.EventAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class WikiListViewModel : ViewModel() {

    val wikiList = MutableLiveData<List<WikipediaEntry>>()
    val wikiListLoad = MutableLiveData<Boolean>()
    val wikiListError = MutableLiveData<Boolean>()

}