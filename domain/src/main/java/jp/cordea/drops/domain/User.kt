package jp.cordea.drops.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
class User(
    val id: UserId,
    val name: String,
    val description: String,
    val imageUrl: String,
    val city: String,
    val rank: Int,
    @Json(name = "created_at") val createdAt: LocalDateTime,
    val birthday: LocalDateTime
)
