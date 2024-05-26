package com.example.hms_projekit.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hms_projekit.R
import com.example.hms_projekit.viewmodel.EventListViewModel


class MainActivity : AppCompatActivity() {



    private lateinit var viewModel : EventListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val month = "1" // Buralar dinamik olarak yapılandırlıcak
        val day = "1"   // Buralar dinamik olarak yapılandırlıcak

        viewModel = ViewModelProvider(this)[EventListViewModel::class.java]
        viewModel.fetchEvents(month,day)

        Log.d("MainActivitye", "Hello")
        observeViewModel()

    }

    //Muhammet Emir Add Friend

    private fun observeViewModel(){
        viewModel.event.observe(this) { events ->
            Log.d("MainActivitye", "Gelen veriler: $events")
        }
        viewModel.eventLoad.observe(this) { isLoading ->

        }
        viewModel.eventError.observe(this) { isError ->

        }

    }
}