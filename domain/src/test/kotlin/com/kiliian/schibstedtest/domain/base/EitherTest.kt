package com.kiliian.schibstedtest.domain.base

import com.kiliian.schibstedtest.domain.UnitTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EitherTest : UnitTest() {

    @Test
    fun `Either Right should return correct value`() {
        val result = Either.Right("test")

        assertTrue { result.isRight }
        assertFalse { result.isLeft }
        result.either({},
            { right ->
                assertEquals("test", right)
            }
        )
    }

    @Test
    fun `Either Left should return correct value`() {
        val result = Either.Left("test")

        assertFalse { result.isRight }
        assertTrue { result.isLeft }
        result.either(
            { left ->
                assertEquals("test", left)
            }, {})
    }
}