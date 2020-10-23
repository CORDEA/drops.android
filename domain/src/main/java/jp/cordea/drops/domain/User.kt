package jp.cordea.drops.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
class User(
    val id: UserId,
    val name: String,
    @Json(name = "created_at") val createdAt: Date
)
