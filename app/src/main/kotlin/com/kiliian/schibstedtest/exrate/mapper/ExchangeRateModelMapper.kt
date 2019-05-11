package com.kiliian.schibstedtest.exrate.mapper

import com.github.mikephil.charting.data.Entry
import com.kiliian.schibstedtest.domain.base.Mapper
import com.kiliian.schibstedtest.domain.exrate.model.ExchangeRate
import javax.inject.Inject

class ExchangeRateModelMapper @Inject constructor() : Mapper<Set<ExchangeRate>, List<Entry>> {

    override fun map(from: Set<ExchangeRate>) = from.toList().map {
        Entry(it.date.toEpochDay().toFloat(), it.rates.values.first())
    }
}