package com.kiliian.schibstedtest.domain.exrate.repository

import com.kiliian.schibstedtest.domain.base.Either
import com.kiliian.schibstedtest.domain.exception.Failure
import com.kiliian.schibstedtest.domain.exrate.model.ExchangeRate
import org.threeten.bp.LocalDate


interface ExchangeRatesRepository {

    fun getRates(
        base: String,
        symbols: List<String>,
        startDate: LocalDate,
        endDate: LocalDate
    ): Either<Failure, Set<ExchangeRate>>
}