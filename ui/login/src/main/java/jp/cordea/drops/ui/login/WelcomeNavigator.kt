package jp.cordea.drops.ui.login

interface WelcomeNavigator {
    fun navigateToLogIn(emailAddress: String)
    fun navigateToSignUp(emailAddress: String)
}
