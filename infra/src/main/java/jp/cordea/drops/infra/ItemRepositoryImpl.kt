package jp.cordea.drops.infra

import jp.cordea.drops.domain.Item
import jp.cordea.drops.domain.ItemDetails
import jp.cordea.drops.domain.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepositoryImpl @Inject internal constructor(
    private val dropsApi: DropsApi
) : ItemRepository {
    override fun findAll(): Flow<List<Item>> =
        dropsApi.getItems().map { it.items }

    override fun find(id: String): Flow<ItemDetails> =
        dropsApi.getItemDetails(id)
}
