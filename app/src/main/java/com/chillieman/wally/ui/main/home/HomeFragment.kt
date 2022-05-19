package com.chillieman.wally.ui.main.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chillieman.wally.R
import com.chillieman.wally.databinding.FragmentHomeBinding
import com.chillieman.wally.model.ProcessingStatus
import com.chillieman.wally.ui.adapter.region.RegionAdapter
import com.chillieman.wally.ui.base.BaseSharedVMFragment
import com.chillieman.wally.ui.main.MainViewModel

class HomeFragment : BaseSharedVMFragment<MainViewModel>(MainViewModel::class.java) {
    private var dialog: AlertDialog? = null

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
            if (it.isNotBlank()) {
                textView.text = it
                textView.visibility = View.VISIBLE
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
            homeViewModel.setText(it)
            when (it) {
                ProcessingStatus.FETCHING -> {} // Empty for now
                ProcessingStatus.LOADED -> {
                    swipeRefresh.isRefreshing = false
                    binding.textHome.setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.green)
                    )
                }
                ProcessingStatus.ERROR -> {
                    swipeRefresh.isRefreshing = false

                    binding.textHome.setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.red)
                    )
                }
                else -> {} //Do nothing if a null status was found
            }
        }

        homeViewModel.dialogText.observe(viewLifecycleOwner) {
            Log.d("Chilliez", "New Dialog Text: $it")
            if (it.isNotBlank()) {
                Log.d("Chilliez", "Creating Dialog: $it")
                dialog = AlertDialog.Builder(requireContext())
                    .setPositiveButton("Ok") { _, _ ->
                        Log.d("Chilliez", "Fetching Regions")
                        sharedViewModel.fetchRegions()
                    }
                    .setMessage(it)
                    .show()
                Log.d("Chilliez", "Clearing Error")
                homeViewModel.clearError()
            }
        }

        swipeRefresh.setOnRefreshListener {
            sharedViewModel.fetchRegions()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        dialog?.dismiss()
    }
}
