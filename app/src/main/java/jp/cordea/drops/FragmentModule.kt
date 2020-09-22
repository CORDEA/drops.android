package jp.cordea.drops

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import jp.cordea.drops.ui.login.*

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {
    @Provides
    fun provideWelcomeNavigator(fragment: Fragment): WelcomeNavigator = object : WelcomeNavigator {
        override fun navigateToLogIn(emailAddress: String) {
            fragment
                .findNavController()
                .navigate(
                    WelcomeFragmentDirections
                        .actionWelcomeFragmentToLogInFragment(emailAddress)
                )
        }

        override fun navigateToSignUp(emailAddress: String) {
            fragment
                .findNavController()
                .navigate(
                    WelcomeFragmentDirections
                        .actionWelcomeFragmentToSignUpFragment(emailAddress)
                )
        }
    }

    @Provides
    fun provideLogInNavigator(fragment: Fragment): LogInNavigator = object : LogInNavigator {
        override fun navigateToMain() {
            fragment
                .findNavController()
                .navigate(
                    LogInFragmentDirections
                        .actionLogInFragmentToMainFragment()
                )
        }
    }

    @Provides
    fun provideSignUpNavigator(fragment: Fragment): SignUpNavigator = object : SignUpNavigator {
        override fun navigateToMain() {
            fragment
                .findNavController()
                .navigate(
                    SignUpFragmentDirections
                        .actionSignUpFragmentToMainFragment()
                )
        }
    }
}
