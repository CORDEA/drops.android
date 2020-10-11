package jp.cordea.drops

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import jp.cordea.drops.ui.NavigationMenuNavigator
import jp.cordea.drops.ui.account.AccountNavigator
import jp.cordea.drops.ui.history.HistoryNavigator
import jp.cordea.drops.ui.login.*
import jp.cordea.drops.ui.main.MainFragmentDirections
import jp.cordea.drops.ui.main.MainNavigator

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

    @Provides
    fun provideMainNavigator(
        fragment: Fragment,
        navigator: NavigationMenuNavigator
    ): MainNavigator = object : MainNavigator, NavigationMenuNavigator by navigator {
        override fun navigateToItem(id: String) {
            fragment
                .findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToItemFragment())
        }

        override fun navigateToCart() {
            fragment
                .findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToCartFragment())
        }
    }

    @Provides
    fun provideHistoryNavigator(
        fragment: Fragment,
        navigator: NavigationMenuNavigator
    ): HistoryNavigator = object : HistoryNavigator, NavigationMenuNavigator by navigator {}

    @Provides
    fun provideAccountNavigator(
        fragment: Fragment,
        navigator: NavigationMenuNavigator
    ): AccountNavigator = object : AccountNavigator, NavigationMenuNavigator by navigator {}

    @Provides
    fun provideNavigationMenuNavigator(fragment: Fragment): NavigationMenuNavigator =
        object : NavigationMenuNavigator {
            override fun navigateToCatalog() {
                fragment
                    .findNavController()
                    .navigate(NavGraphDirections.actionGlobalMainFragment())
            }

            override fun navigateToHistory() {
                fragment
                    .findNavController()
                    .navigate(NavGraphDirections.actionGlobalHistoryFragment())
            }

            override fun navigateToAccount() {
                fragment
                    .findNavController()
                    .navigate(NavGraphDirections.actionGlobalAccountFragment())
            }

            override fun navigateToInquiry() {
                fragment
                    .findNavController()
                    .navigate(NavGraphDirections.actionGlobalInquiryFragment())
            }
        }
}
