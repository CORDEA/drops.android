package jp.cordea.drops.infra

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class SignUpBody(
    @Json(name = "email_address") val emailAddress: String,
    val password: String
)
