package com.example.hms_projekit.util
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hms_projekit.viewmodel.EventListViewModel


class ApplicationViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventListViewModel::class.java)) {
            return EventListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}