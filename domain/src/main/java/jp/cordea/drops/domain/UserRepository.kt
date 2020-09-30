package jp.cordea.drops.domain

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun login(emailAddress: EmailAddress, password: Password): Flow<User>
}
