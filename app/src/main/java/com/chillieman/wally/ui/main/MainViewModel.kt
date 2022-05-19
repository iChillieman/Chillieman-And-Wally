package com.chillieman.wally.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chillieman.wally.model.ProcessingStatus
import com.chillieman.wally.model.RegionItem
import com.chillieman.wally.model.RegionItemForList
import com.chillieman.wally.model.RegionListType
import com.chillieman.wally.repo.RegionRepository
import com.chillieman.wally.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private val repoRegion: RegionRepository
) : BaseViewModel() {

    private val _list = MutableLiveData<List<RegionItemForList>>()
    val list: LiveData<List<RegionItemForList>>
        get() = _list

    private val _processingStatus = MutableLiveData<ProcessingStatus>()
    val processingStatus: LiveData<ProcessingStatus>
        get() = _processingStatus

    fun fetchRegions() {
        _processingStatus.value = ProcessingStatus.FETCHING
        repoRegion.fetchRegions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                val filteredResults = list.filter { !it.isCorrupt }

                if(filteredResults.isNotEmpty()) {
                    val finalList = produceListWithLabels(list)
                    _list.value = finalList
                    _processingStatus.value = ProcessingStatus.LOADED
                } else {
                    _processingStatus.value = ProcessingStatus.ERROR
                }
            }, {
                Log.e("Error!", "BOOO", it)
                _processingStatus.value = ProcessingStatus.ERROR
            }).disposeOnClear()
    }


    private fun produceListWithLabels(list: List<RegionItem>): List<RegionItemForList> {
        var currentLetter = list.first().name.first()
        val finalList = mutableListOf<RegionItemForList>()

        finalList.add(
            RegionItemForList(label = currentLetter.toString(), type = RegionListType.LABEL)
        )

        list.forEach { regionItem ->
            if(!regionItem.name.startsWith(currentLetter, true)) {
                currentLetter = regionItem.name.first()
                finalList.add(
                    RegionItemForList(label = currentLetter.toString(), type = RegionListType.LABEL)
                )

            }
            finalList.add(RegionItemForList(regionItem = regionItem))
        }

        return finalList
    }
}
