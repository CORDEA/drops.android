package jp.cordea.drops.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import jp.cordea.drops.domain.EmailAddress
import jp.cordea.drops.domain.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA

class WelcomeViewModelUnitTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineDispatcherRule = CoroutineDispatcherRule()

    @MockK
    private lateinit var repository: UserRepository

    @InjectMockKs
    private lateinit var viewModel: WelcomeViewModel

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun onKnockClick_logIn() = runBlockingTest {
        every { repository.isUserExists(EmailAddress("e")) } returns flowOf(true)

        val event = async { viewModel.onEvent.receive() }
        viewModel.emailAddress.value = "e"
        viewModel.onKnockClick()

        expectThat(event.await()).isA<WelcomeViewModel.Event.NavigateToLogIn>()
    }

    @Test
    fun onKnockClick_signUp() = runBlockingTest {
        every { repository.isUserExists(EmailAddress("e")) } returns flowOf(false)

        val event = async { viewModel.onEvent.receive() }
        viewModel.emailAddress.value = "e"
        viewModel.onKnockClick()

        expectThat(event.await()).isA<WelcomeViewModel.Event.NavigateToSignUp>()
    }
}
