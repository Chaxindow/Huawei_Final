package com.example.hms_projekit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WikiPageViewModel : ViewModel() {
    val loading = MutableLiveData<Boolean>(false)
    val error = MutableLiveData<Boolean>(false)
}