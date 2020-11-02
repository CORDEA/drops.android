package jp.cordea.drops.infra

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jp.cordea.drops.domain.repository.AchievementRepository
import jp.cordea.drops.domain.repository.CartRepository
import jp.cordea.drops.domain.repository.ItemRepository
import jp.cordea.drops.domain.repository.UserRepository

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
}
