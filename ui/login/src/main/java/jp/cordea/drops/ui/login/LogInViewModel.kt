package jp.cordea.drops.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogInViewModel @ViewModelInject constructor() : ViewModel() {
    val code = MutableLiveData("")

    fun onLogInClick() {
    }
}
