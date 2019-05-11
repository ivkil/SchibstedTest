package com.kiliian.schibstedtest

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

// This class is copy of the same from domain
// Question? should use testImplementation(domain)
@RunWith(MockitoJUnitRunner::class)
abstract class UnitTest {

    @Suppress("LeakingThis")
    @Rule
    @JvmField
    val injectMocks = InjectMocksRule.create(this@UnitTest)
}