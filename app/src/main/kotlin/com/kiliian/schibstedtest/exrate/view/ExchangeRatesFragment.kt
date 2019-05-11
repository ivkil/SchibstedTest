package com.kiliian.schibstedtest.exrate.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.kiliian.schibstedtest.R
import com.kiliian.schibstedtest.base.extensions.*
import com.kiliian.schibstedtest.domain.exception.Failure
import com.kiliian.schibstedtest.exrate.view.chart.DateAxisValueFormatter
import com.kiliian.schibstedtest.exrate.view.chart.ExchangeRateMarkerView
import com.kiliian.schibstedtest.exrate.viewmodel.ExchangeRatesViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ExchangeRatesFragment : DaggerFragment() {

    private val todayEurRateTextView: TextView by bindView(R.id.exchange_rates_eur_rate_text)
    private val todayRateGroup: Group by bindView(R.id.exchange_rates_today_rate_group)
    private val chartView: LineChart by bindView(R.id.exchange_rates_chart_view)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var ratesViewModel: ExchangeRatesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ratesViewModel = viewModel(viewModelFactory) {
            observe(todayRate, ::showTodayRate)
            observe(rates, ::showRatesHistory)
            failure(failure, ::showError)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.kiliian.schibstedtest.R.layout.fragment_exchange_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureChartStyle()
    }

    private fun configureChartStyle() {
        // Setup vertical axes
        chartView.axisLeft.setDrawGridLines(false)
        chartView.axisLeft.setDrawAxisLine(false)
        chartView.axisLeft.setLabelCount(0, true)
        chartView.axisRight.isEnabled = false

        // Setup horizontal axis
        chartView.xAxis.setDrawGridLines(false)
        chartView.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chartView.xAxis.valueFormatter = DateAxisValueFormatter()
        chartView.xAxis.labelCount = 2

        // Disable legend and description
        chartView.legend.isEnabled = false
        chartView.description.isEnabled = false

        // Disable zoom
        chartView.setScaleEnabled(false)
        chartView.isDoubleTapToZoomEnabled = false

        // Setup empty data text and marker
        context?.let {
            chartView.marker = ExchangeRateMarkerView(it)
            chartView.setNoDataTextColor(ContextCompat.getColor(it, R.color.colorTextPrimary))
        }
        chartView.setNoDataText(getString(R.string.loading))
    }

    private fun showTodayRate(rate: Float?) {
        rate?.let {
            todayRateGroup.visible()
            todayEurRateTextView.text = getString(R.string.eur_rate, it)
        }
    }

    private fun showRatesHistory(data: LineDataSet?) {
        context?.let { context ->
            data?.run {
                // Setup line
                mode = LineDataSet.Mode.HORIZONTAL_BEZIER
                cubicIntensity
                color = ContextCompat.getColor(context, R.color.colorPrimary)
                lineWidth = 3f

                // Setup points
                setDrawValues(false)
                setDrawCircles(false)

                // Setup highlight
                setDrawHorizontalHighlightIndicator(false)
                enableDashedHighlightLine(16f, 16f, 16f)
                highLightColor = ContextCompat.getColor(context, R.color.colorAccent)

                // Setup fill
                setDrawFilled(true)
                fillDrawable = ContextCompat.getDrawable(context, R.drawable.exchange_rates_chart_fill)
            }
        }
        chartView.data = LineData(data)
        chartView.invalidate()
    }

    private fun showError(ignored: Failure?) {
        Toast.makeText(context, R.string.generic_error, Toast.LENGTH_LONG).show()
    }
}