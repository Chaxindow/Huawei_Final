package com.example.hms_projekit.view.description

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.hms_projekit.client.WikiClient
import com.example.hms_projekit.databinding.FragmentWikiPageBinding
import com.example.hms_projekit.viewmodel.WikiPageViewModel

class WikiPageFragment : Fragment() {
    private lateinit var binding: FragmentWikiPageBinding
    private val args: WikiPageFragmentArgs by navArgs<WikiPageFragmentArgs>()

    companion object {
        lateinit var viewModel: WikiPageViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[WikiPageViewModel::class.java]
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWikiPageBinding.inflate(layoutInflater, container, false)
        observe()
        binding.webViewWikipedia.loadUrl(args.wikipediaURL)
        binding.webViewWikipedia.webViewClient = WikiClient()
        return binding.root
    }

    private fun observe() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.pbLoadingWiki.visibility = if (isLoading) View.VISIBLE else View.GONE
            with(binding.webViewWikipedia) {
                if (isLoading) {
                    visibility = View.GONE
                } else {
                    visibility = View.GONE
                    if (!viewModel.error.value!!) {
                        visibility = View.VISIBLE
                    }
                }
            }
        }
        viewModel.error.observe(viewLifecycleOwner){isError->
            binding.webViewErrorLayout.visibility  = if (isError) View.VISIBLE else View.GONE
        }
    }
}