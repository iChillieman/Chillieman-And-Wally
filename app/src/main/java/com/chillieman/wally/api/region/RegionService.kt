package com.chillieman.wally.api.region

import com.chillieman.wally.api.region.RegionDefinitions.BASE_URL
import com.chillieman.wally.model.RegionItem
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RegionService @Inject constructor() {
    private val api: RegionAPI
    init {
        val gson = GsonBuilder().registerTypeAdapter(
            RegionItem::class.java,
            RegionDeserializer()
        ).create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        api = retrofit.create(RegionAPI::class.java)
    }

    fun fetchRegions(): Observable<List<RegionItem>> = api.getRegions()

}
