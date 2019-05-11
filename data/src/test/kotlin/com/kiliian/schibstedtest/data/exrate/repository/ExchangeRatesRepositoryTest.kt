package com.kiliian.schibstedtest.data.exrate.repository

import com.kiliian.schibstedtest.data.UnitTest
import com.kiliian.schibstedtest.data.exrate.mapper.ExchangeRateMapper
import com.kiliian.schibstedtest.data.exrate.net.ExchangeRatesApiModel
import com.kiliian.schibstedtest.data.exrate.net.ExchangeRatesService
import com.kiliian.schibstedtest.domain.exception.Failure
import com.kiliian.schibstedtest.domain.exrate.model.ExchangeRate
import com.kiliian.schibstedtest.domain.exrate.repository.ExchangeRatesRepository
import com.nhaarman.mockito_kotlin.check
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.threeten.bp.LocalDate
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ExchangeRatesRepositoryTest : UnitTest() {

    @Mock
    private lateinit var service: ExchangeRatesService
    @Mock
    private lateinit var mapper: ExchangeRateMapper

    @Mock
    private lateinit var call: Call<ExchangeRatesApiModel>
    @Mock
    private lateinit var response: Response<ExchangeRatesApiModel>

    private val base = "USD"
    private val symbols = emptyList<String>()
    private val endDate = LocalDate.now()
    private val startDate = endDate.minusDays(1)

    private lateinit var repository: ExchangeRatesRepository

    @Before
    fun setUp() {
        repository = ExchangeRatesRepositoryImpl(service, mapper)
    }

    @Test
    fun `Should return exchange rates from service`() {
        val apiModel = ExchangeRatesApiModel(base, emptyMap())
        val result = emptySet<ExchangeRate>()

        given { service.history(base, symbols, startDate, endDate) }.willReturn(call)
        given { call.execute() }.willReturn(response)
        given { response.isSuccessful }.willReturn(true)
        given { response.body() }.willReturn(apiModel)
        given { mapper.map(apiModel) }.willReturn(result)

        repository.getRates(base, symbols, startDate, endDate).either({}, { right ->
            assertEquals(result, right)
        })
        verify(service).history(base, symbols, startDate, endDate)
        verify(mapper).map(apiModel)
    }

    @Test
    fun `Should return empty list by default`() {
        given { service.history(base, symbols, startDate, endDate) }.willReturn(call)
        given { call.execute() }.willReturn(response)
        given { response.isSuccessful }.willReturn(true)
        given { response.body() }.willReturn(null)

        repository.getRates(base, symbols, startDate, endDate)
        verify(service).history(base, symbols, startDate, endDate)
        verify(mapper).map(check {
            assertTrue { it.base == base }
            assertTrue { it.rates.isEmpty() }
        })
    }

    @Test
    fun `Should return api failure when bad response`() {
        given { service.history(base, symbols, startDate, endDate) }.willReturn(call)
        given { call.execute() }.willReturn(response)
        given { response.isSuccessful }.willReturn(false)

        repository.getRates(base, symbols, startDate, endDate).either({
            assertTrue { it is Failure.ApiError }
        }, {})
    }

    @Test
    fun `Should return network failure`() {
        given { service.history(base, symbols, startDate, endDate) }.willReturn(call)
        given { call.execute() }.willThrow(IOException())

        repository.getRates(base, symbols, startDate, endDate).either({
            assertTrue { it is Failure.NetworkError }
        }, {})
    }

    @Test
    fun `Should return api failure when error`() {
        given { service.history(base, symbols, startDate, endDate) }.willReturn(call)
        given { call.execute() }.willThrow(RuntimeException())

        repository.getRates(base, symbols, startDate, endDate).either({
            assertTrue { it is Failure.ApiError }
        }, {})
    }
}