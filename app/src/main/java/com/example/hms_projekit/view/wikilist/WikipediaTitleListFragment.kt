package com.example.hms_projekit.view.wikilist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hms_projekit.adapter.WikipediaEntryListAdapter
import com.example.hms_projekit.databinding.FragmentWikipediaTitleListBinding
import com.example.hms_projekit.viewmodel.WikiListViewModel


class WikipediaTitleListFragment : Fragment() {
    private lateinit var binding: FragmentWikipediaTitleListBinding
    private lateinit var viewModel: WikiListViewModel
    private lateinit var adapter: WikipediaEntryListAdapter
    private val args: WikipediaTitleListFragmentArgs by navArgs<WikipediaTitleListFragmentArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[WikiListViewModel::class.java]
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWikipediaTitleListBinding.inflate(layoutInflater, container, false)
        viewModel.wikiList.value = args.wikiItem.toList()
        observe()
        return binding.root
    }

    private fun observe() {
        viewModel.wikiList.observe(viewLifecycleOwner) { wikiList ->
            adapter = WikipediaEntryListAdapter(wikiList) { position: Int ->
                findNavController().navigate(
                    WikipediaTitleListFragmentDirections.actionWikipediaTitleListFragmentToWikiPageFragment(
                        wikiList[position].wikipedia //url yi gönderiyoruz
                    )
                )
            }
            binding.rvWikiTitleList.adapter = adapter
        }
    }

}