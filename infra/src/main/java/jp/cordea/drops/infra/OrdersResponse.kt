package jp.cordea.drops.infra

import com.squareup.moshi.JsonClass
import jp.cordea.drops.domain.Order

@JsonClass(generateAdapter = true)
internal class OrdersResponse(
    val orders: List<Order>
)
