package jp.cordea.drops

import androidx.fragment.app.Fragment
import jp.cordea.drops.ui.Navigator
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
    private val fragment: Fragment
) : Navigator {
    override fun navigateToLogIn(emailAddress: String) {
        TODO("Not yet implemented")
    }

    override fun navigateToSignUp(emailAddress: String) {
        TODO("Not yet implemented")
    }
}
