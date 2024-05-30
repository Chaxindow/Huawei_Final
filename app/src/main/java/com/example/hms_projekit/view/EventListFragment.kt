package com.example.hms_projekit.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hms_projekit.R
import com.example.hms_projekit.adapter.EventListAdapter
import com.example.hms_projekit.databinding.FragmentEventListBinding
import com.example.hms_projekit.viewmodel.EventListViewModel
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.BannerAdSize
import com.huawei.hms.ads.banner.BannerView

class EventListFragment : Fragment() {
    private lateinit var binding: FragmentEventListBinding
    private lateinit var adapter: EventListAdapter
    private lateinit var viewModel: EventListViewModel
    lateinit var bannerView: BannerView

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
        BannerAds()
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
            binding.tvError.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.eventError.observe(viewLifecycleOwner) { isError ->
            binding.pbLoading.visibility = if (isError) View.VISIBLE else View.GONE
        }
    }

    private fun BannerAds(){
        bannerView = binding.hwBannerView
        // Set the ad unit ID and ad dimensions. "testw6vs28auh3" is a dedicated test ad unit ID.
        bannerView.adId = "testw6vs28auh3"
        bannerView.bannerAdSize = BannerAdSize.BANNER_SIZE_360_57
        // Set the refresh interval to 60 seconds.
        bannerView.setBannerRefresh(60)
        // Create an ad request to load an ad.
        val adParam = AdParam.Builder().build()
        bannerView.loadAd(adParam)
    }
}