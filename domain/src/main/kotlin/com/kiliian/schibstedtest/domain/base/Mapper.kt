package com.kiliian.schibstedtest.domain.base

interface Mapper<From, To> {

    fun map(from: From): To
}