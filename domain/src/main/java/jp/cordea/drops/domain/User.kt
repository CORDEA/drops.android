package jp.cordea.drops.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
class User(
    @Json(name = "id") internal val _id: Long,
    val name: String,
    val description: String,
    @Json(name = "image_url") val imageUrl: String,
    val city: String,
    val rank: Int,
    @Json(name = "created_at") val createdAt: LocalDateTime,
    val birthday: LocalDateTime
) {
    val id = UserId(_id)
}
