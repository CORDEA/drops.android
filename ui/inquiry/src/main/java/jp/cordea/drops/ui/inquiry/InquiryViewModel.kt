package jp.cordea.drops.ui.inquiry

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import jp.cordea.drops.ui.NavigationMenuBindable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

class InquiryViewModel @ViewModelInject constructor() : ViewModel(), NavigationMenuBindable {
    private val _onEvent = Channel<Event>()
    val onEvent: ReceiveChannel<Event> get() = _onEvent

    override fun onCatalogClick() {
        _onEvent.offer(Event.ClickedCatalog)
    }

    override fun onHistoryClick() {
        _onEvent.offer(Event.ClickedHistory)
    }

    override fun onAccountClick() {
        _onEvent.offer(Event.ClickedAccount)
    }

    override fun onInquiryClick() {
        _onEvent.offer(Event.ClickedInquiry)
    }

    sealed class Event {
        object ClickedCatalog : Event()
        object ClickedHistory : Event()
        object ClickedAccount : Event()
        object ClickedInquiry : Event()
    }
}
