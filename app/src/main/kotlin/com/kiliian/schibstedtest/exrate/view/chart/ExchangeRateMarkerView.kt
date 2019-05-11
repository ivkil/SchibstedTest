package com.kiliian.schibstedtest.exrate.view.chart

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.kiliian.schibstedtest.R
import com.kiliian.schibstedtest.base.extensions.bindView
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*


class ExchangeRateMarkerView constructor(context: Context) : MarkerView(context, R.layout.exchange_rate_marker) {

    private val rateTextView: TextView by bindView(R.id.exchange_rate_chart_marker_rate_text)
    private val dateTextView: TextView by bindView(R.id.exchange_rate_chart_marker_date_text)

    private val dateFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM", Locale.ENGLISH)

    @SuppressLint("SetTextI18n")
    override fun refreshContent(entry: Entry?, highlight: Highlight?) {
        entry?.let {
            rateTextView.text = "%.2f".format(entry.y)
            dateTextView.text = LocalDate.ofEpochDay(entry.x.toLong()).format(dateFormatter)
        }
        super.refreshContent(entry, highlight)
    }

    override fun getOffset() = MPPointF((-(width + 32)).toFloat(), (-height).toFloat())
}