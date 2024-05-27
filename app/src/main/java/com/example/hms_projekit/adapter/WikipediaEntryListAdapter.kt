package com.example.hms_projekit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hms_projekit.R
import com.example.hms_projekit.databinding.WikipediaItemBinding
import com.example.hms_projekit.model.WikipediaEntry

class WikipediaEntryListAdapter(var wikiList: List<WikipediaEntry>) :
    RecyclerView.Adapter<WikipediaEntryListAdapter.WikiListViewHolder>() {
    class WikiListViewHolder(val itemBinding: WikipediaItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(wikiItem: WikipediaEntry) {
            itemBinding.tvWikiTitle.text = wikiItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WikiListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<WikipediaItemBinding>(
            layoutInflater,
            R.layout.wikipedia_item,
            parent,
            false
        )
        return WikiListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return wikiList.size
    }

    override fun onBindViewHolder(holder: WikiListViewHolder, position: Int) {
        holder.bind(wikiList[position])
    }
}