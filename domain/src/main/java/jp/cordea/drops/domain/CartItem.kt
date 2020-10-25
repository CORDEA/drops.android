package jp.cordea.drops.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CartItem(
    val item: Item,
    val numberOfItems: Int
)
