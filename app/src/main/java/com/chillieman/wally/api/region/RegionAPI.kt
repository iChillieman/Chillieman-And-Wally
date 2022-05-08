package com.chillieman.wally.api.region

import com.chillieman.wally.api.region.RegionDefinitions.PATH_REGIONS
import com.chillieman.wally.model.RegionItem
import io.reactivex.Observable
import retrofit2.http.GET


interface RegionAPI {
    @GET(PATH_REGIONS)
    fun getRegions(): Observable<List<RegionItem>>
}
