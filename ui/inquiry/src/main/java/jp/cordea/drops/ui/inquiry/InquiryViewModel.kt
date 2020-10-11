package jp.cordea.drops.ui.inquiry

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import jp.cordea.drops.ui.NavigationMenuBindable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class InquiryViewModel @ViewModelInject constructor() : ViewModel(), NavigationMenuBindable {
    private val _onEvent = Channel<Event>()
    val onEvent: Flow<Event> get() = _onEvent.receiveAsFlow()

    private val _items = MutableStateFlow(emptyList<InquiryItemViewModel>())
    val items: StateFlow<List<InquiryItemViewModel>> get() = _items

    init {
        _items.value = listOf(
            InquiryItemViewModel(
                0,
                R.drawable.ic_baseline_swap_vertical_circle_40,
                R.string.inquiry_returns_title,
                R.string.inquiry_returns_description
            ),
            InquiryItemViewModel(
                1,
                R.drawable.ic_baseline_help_40,
                R.string.inquiry_inquiry_title,
                R.string.inquiry_inquiry_description
            ),
            InquiryItemViewModel(
                2,
                R.drawable.ic_baseline_code_40,
                R.string.inquiry_licenses_title,
                R.string.inquiry_licenses_description
            )
        )
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

    sealed class Event {
        object NavigateToCatalog : Event()
        object NavigateToHistory : Event()
        object NavigateToAccount : Event()
        object NavigateToInquiry : Event()
    }
}
