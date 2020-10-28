package jp.cordea.drops.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
@JsonClass(generateAdapter = true)
class Item(
    val id: Long,
    val name: String,
    val description: String,
    @Json(name = "image_urls") val imageUrls: List<String>,
    @Json(name = "created_at") val createdAt: LocalDateTime,
    @Json(name = "ended_at") val endedAt: LocalDateTime,
    @Json(name = "updated_at") val updatedAt: LocalDateTime,
    val price: String,
    @Json(name = "currency_code") val currencyCode: String,
    val tags: List<String>,
    val materials: List<String>,
    val views: Int,
    @Json(name = "item_weight") val itemWeight: String,
    @Json(name = "item_weight_unit") val itemWeightUnit: String,
    @Json(name = "item_length") val itemLength: String,
    @Json(name = "item_width") val itemWidth: String,
    @Json(name = "item_height") val itemHeight: String,
    @Json(name = "item_dimensions_unit") val itemDimensionsUnit: String,
    val recipient: String
) : Parcelable
