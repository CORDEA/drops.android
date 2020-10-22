package jp.cordea.drops.infra

import jp.cordea.drops.domain.Achievement
import jp.cordea.drops.domain.repository.AchievementRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AchievementRepositoryImpl @Inject constructor(
    private val dropsApi: DropsApi
) : AchievementRepository {
    override fun findAll(): Flow<List<Achievement>> =
        dropsApi.getAchievements().map { it.achievements }
}
