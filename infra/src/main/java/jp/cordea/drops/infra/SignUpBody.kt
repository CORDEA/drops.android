package jp.cordea.drops.infra

import com.squareup.moshi.Json

internal class SignUpBody(
    @Json(name = "email_address") val emailAddress: String,
    val password: String
)
