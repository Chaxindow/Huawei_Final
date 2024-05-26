package com.example.hms_projekit.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.hms_projekit.R
import com.example.hms_projekit.adapter.EventListAdapter
import com.example.hms_projekit.databinding.FragmentEventListBinding
import com.example.hms_projekit.viewmodel.EventListViewModel

class EventListFragment : Fragment() {
    private lateinit var binding: FragmentEventListBinding
    private lateinit var adapter: EventListAdapter
    private lateinit var viewModel: EventListViewModel
    val month = "1" // Buralar dinamik olarak yapılandırlıcak
    val day = "1"   // Buralar dinamik olarak yapılandırlıcak
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_event_list, container, false)
        viewModel = ViewModelProvider(this)[EventListViewModel::class.java]

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.event.observe(this) { events ->

        }
        viewModel.eventLoad.observe(this) { isLoading ->
            binding.tvError.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.eventError.observe(this) { isError ->
            binding.pbLoading.visibility = if (isError) View.VISIBLE else View.GONE
        }

    }
}