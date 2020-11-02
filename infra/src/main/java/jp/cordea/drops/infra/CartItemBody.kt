package jp.cordea.drops.infra

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class CartItemBody(
    val id: Long
)
