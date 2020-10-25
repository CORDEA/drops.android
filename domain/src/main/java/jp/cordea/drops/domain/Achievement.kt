package jp.cordea.drops.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Achievement(
    val name: String,
    val description: String,
    val level: Int,
    val progress: Float
)
