package jp.cordea.drops.domain.repository

import jp.cordea.drops.domain.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun addItemToCart(id: Long): Flow<List<CartItem>>
}
