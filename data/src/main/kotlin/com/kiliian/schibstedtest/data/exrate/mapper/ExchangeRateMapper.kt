package com.kiliian.schibstedtest.data.exrate.mapper

import com.kiliian.schibstedtest.data.exrate.net.ExchangeRatesApiModel
import com.kiliian.schibstedtest.domain.base.Mapper
import com.kiliian.schibstedtest.domain.exrate.model.ExchangeRate
import javax.inject.Inject

class ExchangeRateMapper @Inject constructor() : Mapper<ExchangeRatesApiModel, Set<ExchangeRate>> {

    override fun map(from: ExchangeRatesApiModel): Set<ExchangeRate> {
        val result = sortedSetOf(compareBy<ExchangeRate> { it.date })
        from.rates.forEach { result += ExchangeRate(from.base, it.key, it.value) }
        return result
    }
}