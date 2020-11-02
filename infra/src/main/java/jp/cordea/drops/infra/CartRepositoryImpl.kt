package jp.cordea.drops.infra

import jp.cordea.drops.domain.CartItem
import jp.cordea.drops.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CartRepositoryImpl @Inject constructor(
    private val dropsApi: DropsApi
) : CartRepository {
    override fun addItemToCart(id: Long): Flow<List<CartItem>> =
        flow { emit(dropsApi.postCartItem(CartItemBody(id))) }
            .map { it.items }
}
