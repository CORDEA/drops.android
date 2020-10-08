package jp.cordea.drops.infra

import jp.cordea.drops.domain.EmailAddress
import jp.cordea.drops.domain.Password
import jp.cordea.drops.domain.User
import jp.cordea.drops.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject internal constructor(
    private val dropsApi: DropsApi
) : UserRepository {
    override fun login(emailAddress: EmailAddress, password: Password): Flow<User> =
        dropsApi.login(LoginBody(emailAddress.emailAddress, password.password))

    override fun signUp(emailAddress: EmailAddress, password: Password): Flow<User> =
        dropsApi.signUp(SignUpBody(emailAddress.emailAddress, password.password))
}
