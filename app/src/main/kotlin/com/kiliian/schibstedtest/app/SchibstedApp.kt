package com.kiliian.schibstedtest.app

import com.jakewharton.threetenabp.AndroidThreeTen
import com.kiliian.schibstedtest.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class SchibstedApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}