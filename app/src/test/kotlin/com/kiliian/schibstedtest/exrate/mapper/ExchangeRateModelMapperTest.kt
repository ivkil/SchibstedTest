package com.kiliian.schibstedtest.exrate.mapper

import com.kiliian.schibstedtest.UnitTest
import com.kiliian.schibstedtest.domain.exrate.model.ExchangeRate
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDate
import kotlin.test.assertEquals

class ExchangeRateModelMapperTest : UnitTest() {

    private lateinit var mapper: ExchangeRateModelMapper

    @Before
    fun setUp() {
        mapper = ExchangeRateModelMapper()
    }

    @Test
    fun `Should map domain model into chart data set`() {
        val date = LocalDate.now()
        val rate = 0.89f

        val result = mapper.map(setOf(ExchangeRate("USD", date, mapOf("EUR" to rate))))
        assertEquals(1, result.size)
        assertEquals(date.toEpochDay().toFloat(), result.first().x)
        assertEquals(rate, result.first().y)
    }
}