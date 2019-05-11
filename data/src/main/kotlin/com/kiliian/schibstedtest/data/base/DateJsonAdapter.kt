package com.kiliian.schibstedtest.data.base

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import org.threeten.bp.LocalDate

class DateJsonAdapter : JsonAdapter<LocalDate>() {

    @Synchronized
    override fun fromJson(reader: JsonReader): LocalDate? = LocalDate.parse(reader.nextString())

    @Synchronized
    override fun toJson(writer: JsonWriter, value: LocalDate?) {
        writer.value(value.toString())
    }
}