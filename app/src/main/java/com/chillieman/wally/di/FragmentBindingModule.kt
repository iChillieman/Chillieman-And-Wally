package com.chillieman.wally.di

import com.chillieman.wally.di.annotation.FragmentScoped
import com.chillieman.wally.ui.main.home.HomeFragment
import com.chillieman.wally.ui.main.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun bindHomeFragment(): HomeFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun bindSettingsFragment(): SettingsFragment
}