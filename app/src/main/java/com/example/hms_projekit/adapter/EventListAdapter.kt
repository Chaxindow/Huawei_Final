package com.example.hms_projekit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hms_projekit.R
import com.example.hms_projekit.databinding.EventItemBinding
import com.example.hms_projekit.model.Event

class EventListAdapter(var eventList: List<Event>, val onClick: (position: Int) -> Unit) :
    Adapter<EventListAdapter.EventListViewHolder>() {
    class EventListViewHolder(val itemBinding: EventItemBinding) : ViewHolder(itemBinding.root) {
        fun bind(event: Event) {
            with(itemBinding) {
                tvYear.text = event.year
                tvDescription.text = event.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<EventItemBinding>(
            layoutInflater,
            R.layout.event_item,
            parent,
            false
        )
        return EventListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        holder.bind(eventList[position])
        holder.itemView.setOnClickListener { onClick(position) }
    }
}