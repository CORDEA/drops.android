package jp.cordea.drops.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.cordea.drops.domain.EmailAddress
import jp.cordea.drops.domain.repository.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WelcomeViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _onEvent = Channel<Event>()
    val onEvent: ReceiveChannel<Event> = _onEvent

    val emailAddress = MutableLiveData("")

    fun onKnockClick() {
        val email = requireNotNull(emailAddress.value)
        repository
            .isUserExists(EmailAddress(email))
            .onEach { isUserExists ->
                _onEvent.offer(
                    if (isUserExists) {
                        Event.NavigateToLogIn(email)
                    } else {
                        Event.NavigateToSignUp(email)
                    }
                )
            }
            .launchIn(viewModelScope)
    }

    sealed class Event {
        class NavigateToLogIn(val emailAddress: String) : Event()
        class NavigateToSignUp(val emailAddress: String) : Event()
    }
}
