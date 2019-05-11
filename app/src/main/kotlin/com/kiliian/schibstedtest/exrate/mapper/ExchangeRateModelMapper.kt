package com.kiliian.schibstedtest.exrate.mapper

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.kiliian.schibstedtest.domain.base.Mapper
import com.kiliian.schibstedtest.domain.exrate.model.ExchangeRate
import javax.inject.Inject

class ExchangeRateModelMapper @Inject constructor() : Mapper<Set<ExchangeRate>, LineDataSet> {

    override fun map(from: Set<ExchangeRate>): LineDataSet {
        val yVals = from.toList().map {
            Entry(it.date.toEpochDay().toFloat(), it.rates.values.first())
        }
        return LineDataSet(yVals, null)
    }
}