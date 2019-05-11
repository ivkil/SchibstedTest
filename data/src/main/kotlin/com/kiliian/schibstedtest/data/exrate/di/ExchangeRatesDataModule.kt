package com.kiliian.schibstedtest.data.exrate.di

import com.kiliian.schibstedtest.data.exrate.net.ExchangeRatesService
import com.kiliian.schibstedtest.data.exrate.repository.ExchangeRatesRepositoryImpl
import com.kiliian.schibstedtest.domain.exrate.repository.ExchangeRatesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class ExchangeRatesDataModule {

    @Singleton
    @Binds
    abstract fun bindRepository(impl: ExchangeRatesRepositoryImpl): ExchangeRatesRepository

    @Module
    companion object {

        @JvmStatic
        @Singleton
        @Provides
        fun provideService(retrofit: Retrofit) = retrofit.create(ExchangeRatesService::class.java)
    }
}