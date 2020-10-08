package jp.cordea.drops.infra

import jp.cordea.drops.domain.ItemDetails
import jp.cordea.drops.domain.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface DropsApi {
    @POST("/user/login")
    fun login(@Body body: LoginBody): Flow<User>

    @POST("/user/new")
    fun signUp(@Body body: SignUpBody): Flow<User>

    @GET("/user/achievements")
    fun getAchievements(): Flow<AchievementsResponse>

    @GET("/items")
    fun getItems(): Flow<ItemsResponse>

    @GET("/items/{id}")
    fun getItemDetails(@Path("id") id: String): Flow<ItemDetails>
}
