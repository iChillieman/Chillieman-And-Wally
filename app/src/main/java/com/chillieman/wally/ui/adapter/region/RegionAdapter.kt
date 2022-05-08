package com.chillieman.wally.ui.adapter.region

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chillieman.wally.databinding.ItemRegionBinding
import com.chillieman.wally.model.RegionItem

class RegionAdapter(
    //val listener: RegionSelectionListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listOfRegions: List<RegionItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRegionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RegionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfRegions.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val region = listOfRegions[position]
        holder as RegionViewHolder
        holder.bind(region)
    }

    fun loadItems(newUserList: List<RegionItem>) {
        listOfRegions = newUserList
        //TODO: In a production environment - Use DiffUtil or code specific logic to not force a
        //      a full reload of the adapter each time loading items
        notifyDataSetChanged()
    }

    inner class RegionViewHolder(
        private val binding: ItemRegionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(region: RegionItem) {
            binding.itemRegionName.text = region.name
            binding.itemRegionCapital.text = region.capital
            binding.itemRegionCode.text = region.code
            binding.itemRegionRegion.text = region.region
        }
    }
}