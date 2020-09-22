package jp.cordea.drops.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

class WelcomeViewModel @ViewModelInject constructor() : ViewModel() {
    private val _onEvent = Channel<Event>()
    val onEvent: ReceiveChannel<Event> = _onEvent

    val emailAddress = MutableLiveData("")

    fun onKnockClick() {
        val email = requireNotNull(emailAddress.value)
        _onEvent.offer(Event.NavigateToLogIn(email))
    }

    sealed class Event {
        class NavigateToLogIn(val emailAddress: String) : Event()
        class NavigateToSignUp(val emailAddress: String) : Event()
    }
}
