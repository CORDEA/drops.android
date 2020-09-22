package jp.cordea.drops.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

class SignUpViewModel @ViewModelInject constructor() : ViewModel() {
    private val _onEvent = Channel<Event>()
    val onEvent: ReceiveChannel<Event> = _onEvent

    val code = MutableLiveData("")

    fun onSignUpClick() {
        _onEvent.offer(Event.NavigateToMain)
    }

    sealed class Event {
        object NavigateToMain : Event()
    }
}
