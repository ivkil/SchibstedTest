package com.kiliian.schibstedtest.base.viewmodel


import androidx.lifecycle.ViewModelProvider
import com.kiliian.schibstedtest.base.di.PerScreen
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @PerScreen
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}