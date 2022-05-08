package com.chillieman.wally.di

import androidx.annotation.CallSuper
import androidx.annotation.UiThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chillieman.wally.di.annotation.ActivityScoped
import com.chillieman.wally.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
}