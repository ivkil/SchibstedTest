package com.kiliian.schibstedtest.data.exrate.net

import org.threeten.bp.LocalDate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ExchangeRatesService {

    @GET("history")
    fun history(
        @Query("base") base: String,
        @Query("symbols") symbols: List<String>,
        @Query("start_at") startDate: LocalDate,
        @Query("end_at") endDate: LocalDate
    ): Call<ExchangeRatesApiModel>
}