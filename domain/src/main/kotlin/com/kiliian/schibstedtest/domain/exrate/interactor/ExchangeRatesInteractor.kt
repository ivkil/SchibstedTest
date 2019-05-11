package com.kiliian.schibstedtest.domain.exrate.interactor

import com.kiliian.schibstedtest.domain.base.Either
import com.kiliian.schibstedtest.domain.exception.Failure
import com.kiliian.schibstedtest.domain.exrate.model.ExchangeRate
import com.kiliian.schibstedtest.domain.exrate.repository.ExchangeRatesRepository
import org.threeten.bp.LocalDate
import javax.inject.Inject

class ExchangeRatesInteractor @Inject constructor(private val repository: ExchangeRatesRepository) {

    fun getUsdToEurLastMonth(): Either<Failure, Set<ExchangeRate>> {
        val today = LocalDate.now()
        return repository.getRates("USD", listOf("EUR"), today.minusMonths(1), today)
    }
}