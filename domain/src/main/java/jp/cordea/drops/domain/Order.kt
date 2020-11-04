package jp.cordea.drops.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
class Order(
    val id: String,
    val status: Status,
    val items: List<Item>,
    @Json(name = "is_cancelable") val isCancelable: Boolean,
    @Json(name = "ordered_at") val orderedAt: LocalDateTime,
    @Json(name = "shipped_at") val shippedAt: LocalDateTime?,
    @Json(name = "delivered_at") val deliveredAt: LocalDateTime?,
    @Json(name = "canceled_at") val canceledAt: LocalDateTime?
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
