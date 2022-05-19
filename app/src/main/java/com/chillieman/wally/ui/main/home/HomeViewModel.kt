package com.chillieman.wally.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chillieman.wally.model.ProcessingStatus
import com.chillieman.wally.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel
@Inject constructor() : BaseViewModel() {
    //Do stuff here that is only required in the Settings Fragment

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome! Swipe Down!"
    }
    val text: LiveData<String> = _text

    private val _dialogText = MutableLiveData<String>()
    val dialogText: LiveData<String> = _dialogText

    fun setText(status: ProcessingStatus) {
        when(status) {
            ProcessingStatus.FETCHING -> removeText()
            ProcessingStatus.LOADED -> setSuccessText()
            ProcessingStatus.ERROR -> setErrorText()
        }
    }
    private fun removeText() {
        _text.value = ""
    }

    private fun setErrorText() {
        _dialogText.value = "An ERROR has occurred."
    }

    fun clearError() {
        _dialogText.value = ""
    }

    private fun setSuccessText() {
        _text.value = "Success!"
    }
}
