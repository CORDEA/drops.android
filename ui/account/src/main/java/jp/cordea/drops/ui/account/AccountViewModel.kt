package jp.cordea.drops.ui.account

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import jp.cordea.drops.ui.NavigationMenuBindable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

class AccountViewModel @ViewModelInject constructor() : ViewModel(), NavigationMenuBindable {
    private val _onEvent = Channel<Event>()
    val onEvent: ReceiveChannel<Event> get() = _onEvent

    override fun onCatalogClick() {
        _onEvent.offer(Event.ClickedCatalog)
    }

    override fun onHistoryClick() {
        _onEvent.offer(Event.ClickedHistory)
    }

    override fun onAccountClick() {
    }

    sealed class Event {
        object ClickedCatalog : Event()
        object ClickedHistory : Event()
    }
}
