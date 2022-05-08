package com.chillieman.wally.ui.adapter.region

import com.chillieman.wally.model.RegionItem

interface RegionSelectionListener {
    fun onRegionItemSelected(item: RegionItem)
}