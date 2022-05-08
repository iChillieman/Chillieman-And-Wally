package com.chillieman.wally.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegionItem(
    val name: String,
    val region: String,
    val code: String,
    val capital: String,
    val isCorrupt: Boolean = false
) : Parcelable {
    companion object {
        fun generateErrorRegionItem() = RegionItem(
            "Unknown","","","", true
        )
    }
}
