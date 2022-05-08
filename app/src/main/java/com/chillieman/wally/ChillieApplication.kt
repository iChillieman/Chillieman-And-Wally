package com.chillieman.wally

import com.chillieman.wally.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class ChillieApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out ChillieApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .apply {
                inject(this@ChillieApplication)
            }
    }
}
