package com.kiliian.schibstedtest.exrate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.LineDataSet
import com.kiliian.schibstedtest.domain.exception.Failure
import com.kiliian.schibstedtest.domain.exrate.interactor.ExchangeRatesInteractor
import com.kiliian.schibstedtest.domain.exrate.model.ExchangeRate
import com.kiliian.schibstedtest.exrate.mapper.ExchangeRateModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExchangeRatesViewModel @Inject constructor(
    private val interactor: ExchangeRatesInteractor,
    private val mapper: ExchangeRateModelMapper
) : ViewModel() {

    val todayRate: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    val rates: MutableLiveData<LineDataSet> by lazy {
        MutableLiveData<LineDataSet>()
    }
    val failure: MutableLiveData<Failure> by lazy {
        MutableLiveData<Failure>()
    }

    init {
        viewModelScope.launch {
            loadRates()
        }
    }

    private suspend fun loadRates() = withContext(Dispatchers.Default) {
        interactor.getUsdToEurLastMonth()
            .either(failure::postValue, ::handleRates)
    }

    private fun handleRates(data: Set<ExchangeRate>) {
        // As set contains sorted values by date asc, last one corresponds for today
        val todayRates = data.last().rates
        todayRate.postValue(todayRates.values.first())

        rates.postValue(mapper.map(data))
    }
}