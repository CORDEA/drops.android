package jp.cordea.drops.infra

import jp.cordea.drops.domain.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST

internal interface LoginApi {
    @POST("/user/login")
    fun login(@Body body: LoginBody): Flow<User>
}
