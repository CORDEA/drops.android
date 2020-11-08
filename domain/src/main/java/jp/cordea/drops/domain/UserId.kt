package jp.cordea.drops.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
inline class UserId(val id: Long)
