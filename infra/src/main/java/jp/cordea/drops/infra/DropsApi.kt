package jp.cordea.drops.infra

import jp.cordea.drops.domain.Order
import jp.cordea.drops.domain.User
import retrofit2.http.*

internal interface DropsApi {
    @POST("/user/login")
    suspend fun login(@Body body: LoginBody): User

    @POST("/user/new")
    suspend fun signUp(@Body body: SignUpBody): User

    @GET("/user/achievements")
    suspend fun getAchievements(): AchievementsResponse

    @GET("/user/cart")
    suspend fun getCartItems(): CartItemsResponse

    @POST("/user/cart")
    suspend fun postCartItem(@Body body: CartItemBody): CartItemsResponse

    @GET("/user/orders")
    suspend fun getOrders(): OrdersResponse

    @PATCH("/user/orders/cancel/{id}")
    suspend fun cancelOrder(@Path("id") id: String): Order

    @GET("/items")
    suspend fun getItems(): ItemsResponse
}
