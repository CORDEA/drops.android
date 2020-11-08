package jp.cordea.drops.domain.repository

import jp.cordea.drops.domain.EmailAddress
import jp.cordea.drops.domain.Password
import jp.cordea.drops.domain.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun login(emailAddress: EmailAddress, password: Password): Flow<User>
    fun signUp(emailAddress: EmailAddress, password: Password): Flow<User>
    fun find(): Flow<User>
}
