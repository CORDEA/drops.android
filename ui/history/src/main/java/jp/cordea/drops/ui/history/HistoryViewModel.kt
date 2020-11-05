package jp.cordea.drops.ui.history

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.cordea.drops.domain.repository.OrderRepository
import jp.cordea.drops.ui.NavigationMenuBindable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.*
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class HistoryViewModel @ViewModelInject constructor(
    private val repository: OrderRepository
) : ViewModel(), NavigationMenuBindable {
    private val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)

    private val _onEvent = Channel<Event>()
    val onEvent: ReceiveChannel<Event> get() = _onEvent

    private val _items = MutableStateFlow(emptyList<HistoryItemViewModel>())
    val items: StateFlow<List<HistoryItemViewModel>> get() = _items

    init {
        refresh()
    }

    override fun onCatalogClick() {
        _onEvent.offer(Event.NavigateToCatalog)
    }

    override fun onHistoryClick() {
        _onEvent.offer(Event.NavigateToHistory)
    }

    override fun onAccountClick() {
        _onEvent.offer(Event.NavigateToAccount)
    }

    override fun onInquiryClick() {
        _onEvent.offer(Event.NavigateToInquiry)
    }

    private fun refresh() {
        repository.findAll()
            .map { list ->
                list.map { order ->
                    val date = order.orderedAt.format(formatter)
                    if (order.isCancelable) {
                        HistoryInProgressItemViewModel(
                            order.id,
                            // TODO
                            order.items.first().imageUrls,
                            date,
                            order.items.joinToString(", ") { it.name }
                        )
                    } else {
                        HistoryCompletedItemViewModel(
                            order.id,
                            // TODO
                            order.items.first().imageUrls.first(),
                            date,
                            order.items.joinToString(", ") { it.name }
                        )
                    }
                }
            }
            .flowOn(Dispatchers.IO)
            .onEach { _items.value = it }
            .launchIn(viewModelScope)
    }

    sealed class Event {
        object NavigateToCatalog : Event()
        object NavigateToHistory : Event()
        object NavigateToAccount : Event()
        object NavigateToInquiry : Event()
    }
}
