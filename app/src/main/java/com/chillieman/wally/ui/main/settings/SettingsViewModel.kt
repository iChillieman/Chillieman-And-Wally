package com.chillieman.wally.ui.main.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chillieman.wally.ui.base.BaseViewModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor()  : BaseViewModel() {
    //Do stuff here that is only required in the Settings Fragment

    private val _text = MutableLiveData<String>().apply {
        value = "I am just here to demonstrate a SharedViewModel"
    }
    val text: LiveData<String> = _text
}
