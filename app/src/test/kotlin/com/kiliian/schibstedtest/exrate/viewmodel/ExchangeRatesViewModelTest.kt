package com.kiliian.schibstedtest.exrate.viewmodel

import com.github.mikephil.charting.data.Entry
import com.kiliian.schibstedtest.AndroidTest
import com.kiliian.schibstedtest.domain.base.Either
import com.kiliian.schibstedtest.domain.exception.Failure
import com.kiliian.schibstedtest.domain.exrate.interactor.ExchangeRatesInteractor
import com.kiliian.schibstedtest.domain.exrate.model.ExchangeRate
import com.kiliian.schibstedtest.exrate.mapper.ExchangeRateModelMapper
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.willReturn
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.threeten.bp.LocalDate
import kotlin.test.assertEquals

class ExchangeRatesViewModelTest : AndroidTest() {

    @Mock
    private lateinit var interactor: ExchangeRatesInteractor
    @Mock
    private lateinit var mapper: ExchangeRateModelMapper

    private lateinit var viewModel: ExchangeRatesViewModel

    @Before
    fun setUp() {
        viewModel = ExchangeRatesViewModel(interactor, mapper)
    }

    @Test
    fun `Loading exchange rates should update today live data`() {
        val todayRate = 0.89f
        val rates = setOf(ExchangeRate("USD", LocalDate.now(), mapOf("EUR" to todayRate)))
        val entries = emptyList<Entry>()
        given { interactor.getUsdToEurLastMonth() }.willReturn { Either.Right(rates) }
        given { mapper.map(rates) }.willReturn(entries)

        viewModel.todayRate.observeForever {
            assertEquals(todayRate, it)
        }
    }

    @Test
    fun `Loading empty data should update only rates live data`() {
        val rates = emptySet<ExchangeRate>()
        val entries = emptyList<Entry>()
        given { interactor.getUsdToEurLastMonth() }.willReturn { Either.Right(rates) }
        given { mapper.map(rates) }.willReturn(entries)

        viewModel.rates.observeForever {
            assertEquals(entries, it)
        }
    }

    @Test
    fun `Error should update failure live data`() {
        val failure = Failure.ApiError
        given { interactor.getUsdToEurLastMonth() }.willReturn { Either.Left(failure) }

        viewModel.failure.observeForever {
            assertEquals(failure, it)
        }
    }
}