package jp.cordea.drops.infra

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jp.cordea.drops.domain.repository.*

@Module
@InstallIn(ApplicationComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindItemRepository(impl: ItemRepositoryImpl): ItemRepository

    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindAchievementRepository(impl: AchievementRepositoryImpl): AchievementRepository

    @Binds
    fun bindCartRepository(impl: CartRepositoryImpl): CartRepository

    @Binds
    fun bindOrderRepository(impl: OrderRepositoryImpl): OrderRepository
}
