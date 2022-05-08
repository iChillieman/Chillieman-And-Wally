package com.chillieman.wally.di

import com.chillieman.wally.ChillieApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class,
        ViewModelBindingModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<ChillieApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(chillieApplication: ChillieApplication): Builder

        fun build(): ApplicationComponent
    }
}