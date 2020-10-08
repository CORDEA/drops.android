package jp.cordea.drops.domain

import kotlinx.coroutines.flow.Flow

interface AchievementRepository {
    fun findAll(): Flow<List<Achievement>>
}
