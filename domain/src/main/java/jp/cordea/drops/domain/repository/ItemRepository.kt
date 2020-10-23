package jp.cordea.drops.domain.repository

import jp.cordea.drops.domain.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun findAll(): Flow<List<Item>>
}
