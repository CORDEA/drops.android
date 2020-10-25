package jp.cordea.drops.infra

import com.squareup.moshi.JsonClass
import jp.cordea.drops.domain.Item

@JsonClass(generateAdapter = true)
internal class ItemsResponse(
    val items: List<Item>
)
