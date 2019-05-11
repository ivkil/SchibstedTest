package com.kiliian.schibstedtest.data.base


import com.kiliian.schibstedtest.data.UnitTest
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.threeten.bp.LocalDate
import kotlin.test.assertEquals

class DateJsonAdapterTest : UnitTest() {

    @Mock
    private lateinit var reader: JsonReader
    @Mock
    private lateinit var writer: JsonWriter

    private lateinit var adapter: DateJsonAdapter

    @Before
    fun setUp() {
        adapter = DateJsonAdapter()
    }

    @Test
    fun `Should convert string to LocalDate`() {
        given { reader.nextString() }.willReturn("2019-05-11")
        assertEquals(LocalDate.of(2019, 5, 11), adapter.fromJson(reader))
    }

    @Test
    fun `Should convert LocalDate to string`() {
        val date = LocalDate.of(2019, 5, 11)
        adapter.toJson(writer, date)
        verify(writer).value("2019-05-11")
    }
}