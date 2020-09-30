package jp.cordea.drops.domain

import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun findAll(): Flow<List<Item>>
}
