package jp.cordea.drops.ui

interface Navigator {
    fun navigateToLogIn(emailAddress: String)
    fun navigateToSignUp(emailAddress: String)
}
