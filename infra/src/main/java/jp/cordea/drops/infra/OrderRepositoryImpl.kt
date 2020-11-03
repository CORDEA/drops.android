package jp.cordea.drops.infra

import jp.cordea.drops.domain.Order
import jp.cordea.drops.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class OrderRepositoryImpl @Inject constructor(
    private val dropsApi: DropsApi
) : OrderRepository {
    override fun findAll(): Flow<List<Order>> =
        flow { emit(dropsApi.getOrders()) }.map { it.orders }
}
