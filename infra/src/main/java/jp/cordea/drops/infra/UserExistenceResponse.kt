package jp.cordea.drops.infra

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class UserExistenceResponse(@Json(name = "is_user_exists") isUserExists: Boolean)
