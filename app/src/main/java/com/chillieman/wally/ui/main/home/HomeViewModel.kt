package com.chillieman.wally.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chillieman.wally.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel
@Inject constructor() : BaseViewModel() {
    //Do stuff here that is only required in the Settings Fragment

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome! Swipe Down!"
    }
    val text: LiveData<String> = _text

    fun removeText() {
        _text.value = ""
    }
}
