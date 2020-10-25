package jp.cordea.drops.infra

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class LocalDateTimeJsonAdapter {
    private val formatter = DateTimeFormatter.ISO_DATE_TIME

    @FromJson
    fun fromJson(json: String): LocalDateTime = LocalDateTime.parse(json, formatter)

    @ToJson
    fun toJson(date: LocalDateTime): String = date.format(formatter)
}
