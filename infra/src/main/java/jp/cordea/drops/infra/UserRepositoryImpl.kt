package jp.cordea.drops.infra

import jp.cordea.drops.domain.AuthenticationCode
import jp.cordea.drops.domain.EmailAddress
import jp.cordea.drops.domain.User
import jp.cordea.drops.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject internal constructor(
    private val dropsApi: DropsApi
) : UserRepository {
    override fun isUserExists(emailAddress: EmailAddress): Flow<Boolean> =
        flow { emit(dropsApi.confirmUserExistence(emailAddress.value)) }.map { it.isUserExists }

    override fun login(emailAddress: EmailAddress, code: AuthenticationCode): Flow<User> =
        flow { emit(dropsApi.login(LoginBody(emailAddress.value, code.value))) }

    override fun signUp(emailAddress: EmailAddress, code: AuthenticationCode): Flow<User> =
        flow { emit(dropsApi.signUp(SignUpBody(emailAddress.value, code.value))) }

    override fun find(): Flow<User> = flow { emit(dropsApi.getUser()) }
}
