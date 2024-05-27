package com.example.hms_projekit.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hms_projekit.R
import com.example.hms_projekit.adapter.EventListAdapter
import com.example.hms_projekit.databinding.FragmentEventListBinding
import com.example.hms_projekit.viewmodel.EventListViewModel

class EventListFragment : Fragment() {
    private lateinit var binding: FragmentEventListBinding
    private lateinit var adapter: EventListAdapter
    private lateinit var viewModel: EventListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[EventListViewModel::class.java]
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_event_list, container, false)
        viewModel.fetchEvents()
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.event.observe(viewLifecycleOwner) { events ->
            adapter = EventListAdapter(events) { position: Int ->
                findNavController().navigate(
                    EventListFragmentDirections.actionEventListFragmentToWikipediaTitleListFragment(
                        events[position].wikipedia.toTypedArray()
                    )
                )
            }
            binding.rvEventList.adapter = adapter
        }
        viewModel.eventLoad.observe(viewLifecycleOwner) { isLoading ->
            binding.pbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.eventError.observe(viewLifecycleOwner) { isError ->
            binding.tvError.visibility = if (isError) View.VISIBLE else View.GONE
        }

    }
}