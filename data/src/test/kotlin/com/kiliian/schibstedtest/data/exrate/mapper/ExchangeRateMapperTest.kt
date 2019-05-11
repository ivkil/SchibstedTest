package com.kiliian.schibstedtest.data.exrate.mapper

import com.kiliian.schibstedtest.data.UnitTest
import com.kiliian.schibstedtest.data.exrate.net.ExchangeRatesApiModel
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDate
import kotlin.test.assertEquals

class ExchangeRateMapperTest : UnitTest() {

    private lateinit var mapper: ExchangeRateMapper

    @Before
    fun setUp() {
        mapper = ExchangeRateMapper()
    }

    @Test
    fun `Should map server response to domain model`() {
        val base = "USD"
        val symbol = "EUR"
        val date = LocalDate.now()
        val rate = 0.89f
        val apiModel = ExchangeRatesApiModel(base, mapOf(date to mapOf(symbol to rate)))

        val domainModel = mapper.map(apiModel)

        assertEquals(1, domainModel.size)
        assertEquals(base, domainModel.first().base)
        assertEquals(date, domainModel.first().date)
        assertEquals(1, domainModel.first().rates.size)
        assertEquals(symbol, domainModel.first().rates.keys.first())
        assertEquals(rate, domainModel.first().rates.values.first())
    }
}