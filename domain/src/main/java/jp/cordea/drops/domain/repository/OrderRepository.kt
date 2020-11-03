package jp.cordea.drops.domain.repository

import jp.cordea.drops.domain.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun findAll(): Flow<List<Order>>
}
