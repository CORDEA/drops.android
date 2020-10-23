package jp.cordea.drops.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
class Item(
    val id: String,
    val name: String,
    val description: String,
    @Json(name = "image_urls") val imageUrls: List<String>,
    @Json(name = "created_at") val createdAt: Date,
    @Json(name = "ended_at") val endedAt: Date,
    @Json(name = "updated_at") val updatedAt: Date,
    val price: String,
    @Json(name = "currency_code") val currencyCode: String,
    val tags: List<String>,
    val materials: List<String>,
    val views: Int,
    @Json(name = "item_weight") val itemWeight: Int,
    @Json(name = "item_weight_unit") val itemWeightUnit: String,
    @Json(name = "item_length") val itemLength: Int,
    @Json(name = "item_width") val itemWidth: Int,
    @Json(name = "item_height") val itemHeight: Int,
    @Json(name = "item_dimensions_unit") val itemDimensionsUnit: String,
    val recipient: String
)
