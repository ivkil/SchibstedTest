package com.kiliian.schibstedtest

import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

// This class is copy of the same from domain
// Question? should use testImplementation(domain)
class InjectMocksRule {

    companion object {
        fun create(testClass: Any) = TestRule { statement, _ ->
            MockitoAnnotations.initMocks(testClass)
            statement
        }
    }
}
