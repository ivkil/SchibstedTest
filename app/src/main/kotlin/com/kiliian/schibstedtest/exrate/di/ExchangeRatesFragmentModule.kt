package com.kiliian.schibstedtest.exrate.di

import com.kiliian.schibstedtest.base.di.PerScreen
import com.kiliian.schibstedtest.base.viewmodel.ViewModelModule
import com.kiliian.schibstedtest.exrate.view.ExchangeRatesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ExchangeRatesFragmentModule {

    @PerScreen
    @ContributesAndroidInjector(modules = [ViewModelModule::class, ExchangeRatesPresentationModule::class])
    abstract fun bindExchangeRatesFragment(): ExchangeRatesFragment
}