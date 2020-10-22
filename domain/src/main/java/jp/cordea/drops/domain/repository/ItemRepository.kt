package jp.cordea.drops.domain.repository

import jp.cordea.drops.domain.Item
import jp.cordea.drops.domain.ItemDetails
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun findAll(): Flow<List<Item>>
    fun find(id: String): Flow<ItemDetails>
}
