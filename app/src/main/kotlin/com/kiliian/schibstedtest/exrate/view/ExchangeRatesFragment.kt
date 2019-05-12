package com.kiliian.schibstedtest.exrate.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.kiliian.schibstedtest.R
import com.kiliian.schibstedtest.base.extensions.viewModel
import com.kiliian.schibstedtest.base.extensions.visible
import com.kiliian.schibstedtest.exrate.view.chart.DateAxisValueFormatter
import com.kiliian.schibstedtest.exrate.view.chart.ExchangeRateMarkerView
import com.kiliian.schibstedtest.exrate.viewmodel.ExchangeRatesViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ExchangeRatesFragment : DaggerFragment() {

    private lateinit var todayRateTextView: TextView
    private lateinit var todayRateGroup: Group
    private lateinit var chartView: LineChart

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var ratesViewModel: ExchangeRatesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ratesViewModel = viewModel(viewModelFactory)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todayRateTextView = view.findViewById(R.id.exchange_rates_eur_rate_text)
        todayRateGroup = view.findViewById(R.id.exchange_rates_today_rate_group)
        chartView = view.findViewById(R.id.exchange_rates_chart_view)

        configureChartStyle()
        subscribeForData()
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

        // Setup empty data
        chartView.setNoDataTextColor(ContextCompat.getColor(context!!, R.color.colorTextPrimary))
        chartView.setNoDataText(getString(R.string.loading))

        // Setup view displayed when selecting values
        chartView.marker = ExchangeRateMarkerView(context!!)
    }

    private fun subscribeForData() {
        ratesViewModel.todayRate.observe(viewLifecycleOwner, Observer { showTodayRate(it) })
        ratesViewModel.rates.observe(viewLifecycleOwner, Observer { showRatesHistory(it) })
        ratesViewModel.failure.observe(viewLifecycleOwner, Observer { showGenericError() })
    }

    private fun showTodayRate(rate: Float) {
        todayRateGroup.visible()
        todayRateTextView.text = getString(R.string.eur_rate, rate)
    }

    private fun showRatesHistory(entries: List<Entry>) {
        val data = LineDataSet(entries, null).apply {
            // Setup line
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            color = ContextCompat.getColor(context!!, R.color.colorPrimary)
            lineWidth = 3f

            // Setup points
            setDrawValues(false)
            setDrawCircles(false)

            // Setup highlight
            setDrawHorizontalHighlightIndicator(false)
            enableDashedHighlightLine(16f, 16f, 16f)
            highLightColor = ContextCompat.getColor(context!!, R.color.colorAccent)

            // Setup fill
            setDrawFilled(true)
            fillDrawable = ContextCompat.getDrawable(context!!, R.drawable.exchange_rates_chart_fill)
        }
        chartView.data = LineData(data)
        chartView.invalidate()
    }

    private fun showGenericError() {
        Toast.makeText(context, R.string.generic_error, Toast.LENGTH_LONG).show()
    }
}