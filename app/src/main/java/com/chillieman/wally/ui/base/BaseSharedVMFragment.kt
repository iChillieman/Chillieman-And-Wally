package com.chillieman.wally.ui.base

import androidx.annotation.UiThread
import androidx.lifecycle.ViewModelProvider
import com.chillieman.wally.di.ViewModelFactory
import javax.inject.Inject

abstract class BaseSharedVMFragment<T : BaseViewModel>(
    @JvmSuppressWildcards private val viewModelClass: Class<T>
) : BaseFragment() {
    @Inject
    lateinit var vmFactory: ViewModelFactory

    val sharedViewModel: T by lazy {
        ViewModelProvider(requireActivity(), vmFactory)[viewModelClass]
    }
        @UiThread
        get
}
