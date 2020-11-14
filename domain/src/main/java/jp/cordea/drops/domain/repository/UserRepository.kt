package jp.cordea.drops.domain.repository

import jp.cordea.drops.domain.AuthenticationCode
import jp.cordea.drops.domain.EmailAddress
import jp.cordea.drops.domain.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun isUserExists(emailAddress: EmailAddress): Flow<Boolean>
    fun login(emailAddress: EmailAddress, code: AuthenticationCode): Flow<User>
    fun signUp(emailAddress: EmailAddress, code: AuthenticationCode): Flow<User>
    fun find(): Flow<User>
}
