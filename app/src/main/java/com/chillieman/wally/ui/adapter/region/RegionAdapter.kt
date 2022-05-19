package com.chillieman.wally.ui.adapter.region

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chillieman.wally.databinding.ItemRegionBinding
import com.chillieman.wally.databinding.ItemRegionLabelBinding
import com.chillieman.wally.model.RegionItem
import com.chillieman.wally.model.RegionItemForList
import com.chillieman.wally.model.RegionListType
import java.lang.IllegalArgumentException


class RegionAdapter(
    //val listener: RegionSelectionListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listOfRegions: List<RegionItemForList> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == RegionListType.REGION.viewId) {
            //Region Item
            val binding = ItemRegionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return RegionViewHolder(binding)
        } else {
            //Label Item
            val binding = ItemRegionLabelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return LabelViewHolder(binding)
        }

    }

    override fun getItemCount(): Int {
        return listOfRegions.size
    }

    override fun getItemViewType(position: Int): Int {
        return listOfRegions[position].type.viewId
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val region = listOfRegions[position]
        when(region.type) {
            RegionListType.REGION -> {
                holder as RegionViewHolder
                holder.bind(region.regionItem ?: throw IllegalArgumentException("Region Item is corrupt"))
            }
            RegionListType.LABEL -> {
                holder as LabelViewHolder
                holder.bind(region.label ?: throw IllegalArgumentException("Region Label is corrupt"))
            }
        }
    }

    fun loadItems(newUserList: List<RegionItemForList>) {
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

    inner class LabelViewHolder(
        private val binding: ItemRegionLabelBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(label: String?) {
            binding.itemRegionLabel.text = label
        }
    }
}