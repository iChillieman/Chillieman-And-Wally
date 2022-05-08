package com.chillieman.wally.api.region

import android.util.Log
import com.chillieman.wally.api.region.RegionDefinitions.TAG_REGION_CAPITAL
import com.chillieman.wally.api.region.RegionDefinitions.TAG_REGION_CODE
import com.chillieman.wally.api.region.RegionDefinitions.TAG_REGION_NAME
import com.chillieman.wally.api.region.RegionDefinitions.TAG_REGION_REGION
import com.chillieman.wally.model.RegionItem
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.Exception
import java.lang.reflect.Type

class RegionDeserializer: JsonDeserializer<RegionItem> {

    @Override
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RegionItem {
        json?.let {
            try {
                val element = it.asJsonObject
                val name = element[TAG_REGION_NAME].asString
                val region = element[TAG_REGION_REGION].asString
                val code = element[TAG_REGION_CODE].asString
                val capital = element[TAG_REGION_CAPITAL].asString
                return RegionItem(name, region, code, capital)

            } catch (e: Exception) {
                Log.e(TAG, "Could not read Region JSON Item", e)
            }
        }

        // Ill assume we dont want to display any item that is missing the 4 expected labels
        return RegionItem.generateErrorRegionItem()
    }

    companion object {
        private const val TAG = "Chillie"
    }
}
