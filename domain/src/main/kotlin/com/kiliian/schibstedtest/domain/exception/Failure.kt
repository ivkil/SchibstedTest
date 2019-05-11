package com.kiliian.schibstedtest.domain.exception

sealed class Failure {
    object NetworkError : Failure()
    object ApiError : Failure()
}