package com.kiliian.schibstedtest.data.exrate.net

import com.squareup.moshi.Json
import org.threeten.bp.LocalDate

data class ExchangeRatesApiModel(
    @Json(name = "base") val base: String,
    @Json(name = "rates") val rates: Map<LocalDate, Map<String, Float>>
)