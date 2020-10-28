package jp.cordea.drops.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.cordea.drops.domain.repository.ItemRepository
import jp.cordea.drops.ui.NavigationMenuBindable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.*

class MainViewModel @ViewModelInject constructor(
    private val repository: ItemRepository
) : ViewModel(),
    NavigationMenuBindable,
    MainItem.OnItemClickListener {
    private val _onEvent = Channel<Event>()
    val onEvent: ReceiveChannel<Event> get() = _onEvent

    private val _items = MutableStateFlow(emptyList<MainItemViewModel>())
    val items: StateFlow<List<MainItemViewModel>> get() = _items

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

    override fun onItemClick(id: Long) {
        _onEvent.offer(Event.NavigateToItem(id))
    }

    fun onCartClick() {
        _onEvent.offer(Event.NavigateToCart)
    }

    private fun refresh() {
        repository.findAll()
            .map { list -> list.map { MainItemViewModel(it.id, it.imageUrls.first()) } }
            .flowOn(Dispatchers.IO)
            .onEach { _items.value = it }
            .launchIn(viewModelScope)
    }

    sealed class Event {
        object NavigateToCatalog : Event()
        object NavigateToHistory : Event()
        object NavigateToAccount : Event()
        object NavigateToInquiry : Event()
        object NavigateToCart : Event()
        class NavigateToItem(val id: Long) : Event()
    }
}
