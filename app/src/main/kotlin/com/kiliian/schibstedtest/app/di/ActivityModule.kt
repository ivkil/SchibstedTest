package com.kiliian.schibstedtest.app.di

import com.kiliian.schibstedtest.app.SingleActivity
import com.kiliian.schibstedtest.base.di.PerActivity
import com.kiliian.schibstedtest.exrate.di.ExchangeRatesFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [ExchangeRatesFragmentModule::class])
    abstract fun bindSingleActivity(): SingleActivity
}