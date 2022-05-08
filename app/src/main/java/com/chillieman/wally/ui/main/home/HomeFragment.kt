package com.chillieman.wally.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chillieman.wally.databinding.FragmentHomeBinding
import com.chillieman.wally.model.ProcessingStatus
import com.chillieman.wally.ui.adapter.region.RegionAdapter
import com.chillieman.wally.ui.base.BaseSharedVMFragment
import com.chillieman.wally.ui.main.MainViewModel

class HomeFragment : BaseSharedVMFragment<MainViewModel>(MainViewModel::class.java) {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val swipeRefresh get() = binding.root

    private val adapter: RegionAdapter by lazy { RegionAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            if(it.isNotBlank()) {
                textView.text = it
            } else {
                textView.visibility = View.GONE
            }
        }

        // Handle RecyclerView
        binding.rvRegionList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRegionList.adapter = adapter
        sharedViewModel.list.observe(viewLifecycleOwner) { regions ->
            adapter.loadItems(regions)
        }


        sharedViewModel.processingStatus.observe(viewLifecycleOwner) {
            when(it) {
                ProcessingStatus.FETCHING -> {
                    //TODO - Remove any error message
                }
                ProcessingStatus.LOADED -> { swipeRefresh.isRefreshing = false }
                ProcessingStatus.ERROR -> {
                    swipeRefresh.isRefreshing = false
                    //TODO - Display an error
                }
                else -> {} //Do nothing if a null status was found
            }
        }

        swipeRefresh.setOnRefreshListener {
            homeViewModel.removeText()
            sharedViewModel.fetchRegions()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
