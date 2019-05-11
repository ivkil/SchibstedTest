package com.kiliian.schibstedtest.app.di

import com.kiliian.schibstedtest.app.SchibstedApp
import com.kiliian.schibstedtest.data.base.di.NetworkModule
import com.kiliian.schibstedtest.data.exrate.di.ExchangeRatesDataModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityModule::class,
        NetworkModule::class,
        ExchangeRatesDataModule::class
    ]
)
interface AppComponent : AndroidInjector<SchibstedApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<SchibstedApp>
}