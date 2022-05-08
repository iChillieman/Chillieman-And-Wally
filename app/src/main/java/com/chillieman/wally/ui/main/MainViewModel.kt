package com.chillieman.wally.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chillieman.wally.model.ProcessingStatus
import com.chillieman.wally.model.RegionItem
import com.chillieman.wally.repo.RegionRepository
import com.chillieman.wally.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private val repoRegion: RegionRepository
) : BaseViewModel() {

    private val _list = MutableLiveData<List<RegionItem>>()
    val list: LiveData<List<RegionItem>>
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
                _list.value = list.filter { !it.isCorrupt }
                _processingStatus.value = ProcessingStatus.LOADED
            }, {
                Log.e("Error!", "BOOO", it)
                _processingStatus.value = ProcessingStatus.ERROR
            }).disposeOnClear()
    }
}
