package com.chillieman.wally.repo

import com.chillieman.wally.api.region.RegionService
import com.chillieman.wally.model.RegionItem
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegionRepository
@Inject constructor(
    private val regionService: RegionService
) {
    fun fetchRegions(): Observable<List<RegionItem>> = regionService.fetchRegions()
            //TODO - Create a database and interact with it here

}
