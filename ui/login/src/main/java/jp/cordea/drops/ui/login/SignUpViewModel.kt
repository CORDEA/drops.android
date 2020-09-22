package jp.cordea.drops.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel @ViewModelInject constructor() : ViewModel() {
    val code = MutableLiveData("")

    fun onSignUpClick() {
    }
}
