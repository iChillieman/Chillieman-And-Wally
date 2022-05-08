package com.chillieman.wally.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chillieman.wally.di.annotation.ViewModelKey
import com.chillieman.wally.ui.main.MainViewModel
import com.chillieman.wally.ui.main.home.HomeViewModel
import com.chillieman.wally.ui.main.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelBindingModule {
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}