package jp.cordea.drops.ui.account

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.cordea.drops.domain.repository.UserRepository
import jp.cordea.drops.ui.NavigationMenuBindable
import jp.cordea.drops.ui.ResourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.*
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class AccountViewModel @ViewModelInject constructor(
    private val resourceProvider: ResourceProvider,
    private val repository: UserRepository
) : ViewModel(), NavigationMenuBindable {
    private val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)

    private val _onEvent = Channel<Event>()
    val onEvent: ReceiveChannel<Event> get() = _onEvent

    private val _items = MutableStateFlow(emptyList<AccountItemViewModel>())
    val items: StateFlow<List<AccountItemViewModel>> get() = _items

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
        repository.find()
            .map {
                listOf(
                    AccountHeaderItemViewModel(
                        it.imageUrl,
                        it.name,
                        it.description
                    ),
                    AccountListItemViewModel(
                        resourceProvider.getString(R.string.account_item_rank),
                        it.rank.toString()
                    ),
                    AccountListItemViewModel(
                        resourceProvider.getString(R.string.account_item_city),
                        it.city
                    ),
                    AccountListItemViewModel(
                        resourceProvider.getString(R.string.account_item_birthday),
                        it.birthday.format(formatter)
                    )
                )
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
