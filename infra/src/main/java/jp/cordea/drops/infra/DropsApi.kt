package jp.cordea.drops.infra

import jp.cordea.drops.domain.ItemDetails
import jp.cordea.drops.domain.Order
import jp.cordea.drops.domain.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

internal interface DropsApi {
    @POST("/user/login")
    fun login(@Body body: LoginBody): Flow<User>

    @POST("/user/new")
    fun signUp(@Body body: SignUpBody): Flow<User>

    @GET("/user/achievements")
    fun getAchievements(): Flow<AchievementsResponse>

    @GET("/user/cart")
    fun getCartItems(): Flow<CartItemsResponse>

    @POST("/user/cart")
    fun postCartItem(@Body body: CartItemBody): Flow<CartItemsResponse>

    @GET("/user/orders")
    fun getOrders(): Flow<OrdersResponse>

    @PATCH("/user/orders/cancel/{id}")
    fun cancelOrder(@Path("id") id: String): Flow<Order>

    @GET("/items")
    fun getItems(): Flow<ItemsResponse>

    @GET("/items/{id}")
    fun getItemDetails(@Path("id") id: String): Flow<ItemDetails>
}
