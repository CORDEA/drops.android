package jp.cordea.drops.infra

import com.squareup.moshi.JsonClass
import jp.cordea.drops.domain.CartItem

@JsonClass(generateAdapter = true)
internal class CartItemsResponse(
    val items: List<CartItem>
)
