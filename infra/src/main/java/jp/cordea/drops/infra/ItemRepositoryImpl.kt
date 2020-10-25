package jp.cordea.drops.infra

import jp.cordea.drops.domain.Item
import jp.cordea.drops.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepositoryImpl @Inject internal constructor(
    private val dropsApi: DropsApi
) : ItemRepository {
    override fun findAll(): Flow<List<Item>> =
        flow { emit(dropsApi.getItems()) }.map { it.items }
}
