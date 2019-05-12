package com.kiliian.schibstedtest.domain.exrate.interactor

import com.kiliian.schibstedtest.domain.UnitTest
import com.kiliian.schibstedtest.domain.base.Either
import com.kiliian.schibstedtest.domain.exrate.model.ExchangeRate
import com.kiliian.schibstedtest.domain.exrate.repository.ExchangeRatesRepository
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.threeten.bp.LocalDate
import kotlin.test.assertEquals

class ExchangeRatesInteractorTest : UnitTest() {

    @Mock
    private lateinit var repository: ExchangeRatesRepository

    private lateinit var interactor: ExchangeRatesInteractor

    @Before
    fun setUp() {
        interactor = ExchangeRatesInteractor(repository)
    }

    @Test
    fun `Interactor should get data from repository by passing USD-EUR and last month period`() {
        val result = Either.Right<Set<ExchangeRate>>(emptySet())
        given { repository.getRates(anyString(), anyList(), any(), any()) }
            .willReturn { result }

        assertEquals(result, interactor.getUsdToEurLastMonth())
        verify(repository).getRates(
            eq("USD"),
            check {
                assertEquals(1, it.size)
                assertEquals("EUR", it.first())
            },
            check { assertEquals(LocalDate.now().minusMonths(1), it) },
            check { assertEquals(LocalDate.now(), it) }
        )
    }
}