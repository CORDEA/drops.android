package jp.cordea.drops.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.cordea.drops.domain.AuthenticationCode
import jp.cordea.drops.domain.EmailAddress
import jp.cordea.drops.domain.repository.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SignUpViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _onEvent = Channel<Event>()
    val onEvent: ReceiveChannel<Event> = _onEvent

    val code = MutableLiveData("")

    private var emailAddress: EmailAddress? = null

    fun init(emailAddress: String) {
        this.emailAddress = EmailAddress(emailAddress)
    }

    fun onSignUpClick() {
        val code = requireNotNull(code.value)
        repository
            .signUp(requireNotNull(emailAddress), AuthenticationCode(code))
            .onEach { _onEvent.offer(Event.NavigateToMain) }
            .launchIn(viewModelScope)
    }

    sealed class Event {
        object NavigateToMain : Event()
    }
}
