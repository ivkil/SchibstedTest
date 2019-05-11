package com.kiliian.schibstedtest.domain.exrate.model

import org.threeten.bp.LocalDate


data class ExchangeRate(val base: String, val date: LocalDate, val rates: Map<String, Float>)