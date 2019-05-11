package com.kiliian.schibstedtest.exrate.di

import androidx.lifecycle.ViewModel
import com.kiliian.schibstedtest.base.di.PerScreen
import com.kiliian.schibstedtest.base.viewmodel.ViewModelKey
import com.kiliian.schibstedtest.exrate.viewmodel.ExchangeRatesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ExchangeRatesPresentationModule {

    @PerScreen
    @Binds
    @IntoMap
    @ViewModelKey(ExchangeRatesViewModel::class)
    abstract fun bindExchangeRatesViewModel(impl: ExchangeRatesViewModel): ViewModel
}