package jp.cordea.drops.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel @ViewModelInject constructor() : ViewModel() {
    val emailAddress = MutableLiveData("")

    fun onKnockClick() {
    }
}
