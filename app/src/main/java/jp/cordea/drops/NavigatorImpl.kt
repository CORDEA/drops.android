package jp.cordea.drops

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import jp.cordea.drops.ui.Navigator
import jp.cordea.drops.ui.login.WelcomeFragmentDirections
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
    private val fragment: Fragment
) : Navigator {
    override fun navigateToLogIn(emailAddress: String) {
        navigate(
            WelcomeFragmentDirections
                .actionWelcomeFragmentToLogInFragment(emailAddress)
        )
    }

    override fun navigateToSignUp(emailAddress: String) {
        navigate(
            WelcomeFragmentDirections
                .actionWelcomeFragmentToSignUpFragment(emailAddress)
        )
    }

    private fun navigate(directions: NavDirections) {
        fragment
            .findNavController()
            .navigate(directions)
    }
}
