package com.kiliian.schibstedtest.exrate.view.chart

import com.github.mikephil.charting.formatter.ValueFormatter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class DateAxisValueFormatter : ValueFormatter() {

    private val dateFormatter = DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH)

    override fun getFormattedValue(value: Float): String =
        LocalDate.ofEpochDay(value.toLong()).format(dateFormatter)
}