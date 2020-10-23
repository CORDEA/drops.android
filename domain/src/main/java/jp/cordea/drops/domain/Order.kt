package jp.cordea.drops.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Order(
    val id: String,
    val status: Status,
    val items: List<Item>,
    @Json(name = "is_cancelable") val isCancelable: Boolean
) {
    enum class Status {
        @Json(name = "confirmed")
        CONFIRMED,

        @Json(name = "shipped")
        SHIPPED,

        @Json(name = "delivered")
        DELIVERED,

        @Json(name = "canceled")
        CANCELED
    }
}
