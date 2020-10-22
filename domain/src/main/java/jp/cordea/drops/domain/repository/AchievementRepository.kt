package jp.cordea.drops.domain.repository

import jp.cordea.drops.domain.Achievement
import kotlinx.coroutines.flow.Flow

interface AchievementRepository {
    fun findAll(): Flow<List<Achievement>>
}
