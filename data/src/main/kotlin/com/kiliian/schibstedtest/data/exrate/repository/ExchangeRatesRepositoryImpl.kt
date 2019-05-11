package com.kiliian.schibstedtest.data.exrate.repository

import com.kiliian.schibstedtest.data.exrate.mapper.ExchangeRateMapper
import com.kiliian.schibstedtest.data.exrate.net.ExchangeRatesApiModel
import com.kiliian.schibstedtest.data.exrate.net.ExchangeRatesService
import com.kiliian.schibstedtest.domain.base.Either
import com.kiliian.schibstedtest.domain.exception.Failure
import com.kiliian.schibstedtest.domain.exrate.model.ExchangeRate
import com.kiliian.schibstedtest.domain.exrate.repository.ExchangeRatesRepository
import org.threeten.bp.LocalDate
import retrofit2.Call
import java.io.IOException
import javax.inject.Inject

class ExchangeRatesRepositoryImpl @Inject constructor(
    private val service: ExchangeRatesService,
    private val mapper: ExchangeRateMapper
) : ExchangeRatesRepository {

    override fun getRates(
        base: String,
        symbols: List<String>,
        startDate: LocalDate,
        endDate: LocalDate
    ): Either<Failure, Set<ExchangeRate>> {
        return request(
            service.history(base, symbols, startDate, endDate),
            { mapper.map(it) },
            ExchangeRatesApiModel(base, emptyMap())
        )
    }

    private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                Either.Right(transform((response.body() ?: default)))
            } else {
                Either.Left(Failure.ApiError)
            }
        } catch (exception: Throwable) {
            return when (exception) {
                is IOException -> Either.Left(Failure.NetworkError)
                else -> Either.Left(Failure.ApiError)
            }
        }
    }
}