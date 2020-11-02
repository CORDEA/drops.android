package jp.cordea.drops.ui.item

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import jp.cordea.drops.domain.Item
import jp.cordea.drops.domain.repository.CartRepository
import jp.cordea.drops.ui.ResourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber

class ItemViewModel @ViewModelInject constructor(
    private val repository: CartRepository,
    private val resourceProvider: ResourceProvider
) : ViewModel() {
    private val _images = MutableStateFlow(emptyList<ImageItemViewModel>())
    val images: StateFlow<List<ImageItemViewModel>> get() = _images

    private val _items = MutableStateFlow(emptyList<ListItemViewModel>())
    val items: StateFlow<List<ListItemViewModel>> get() = _items

    private val _tags = MutableStateFlow(emptyList<String>())
    val tags: StateFlow<List<String>> get() = _tags

    private val _buttonText = MutableStateFlow("")
    val buttonText: StateFlow<String> get() = _buttonText

    private val item = MutableLiveData<Item>()
    val name = item.map { it.name }
    val description = item.map { it.description }

    fun init(item: Item) {
        this.item.value = item
        _buttonText.value =
            resourceProvider.getString(R.string.button_text_format, item.currencyCode, item.price)
        _images.value = item.imageUrls.map { ImageItemViewModel(it) }
        _tags.value = item.tags
        _items.value = listOf(
            ListItemViewModel(
                resourceProvider.getString(R.string.label_item_height),
                resourceProvider.getString(
                    R.string.item_body_format,
                    item.itemHeight,
                    item.itemDimensionsUnit
                )
            ),
            ListItemViewModel(
                resourceProvider.getString(R.string.label_item_width),
                resourceProvider.getString(
                    R.string.item_body_format,
                    item.itemWidth,
                    item.itemDimensionsUnit
                )
            ),
            ListItemViewModel(
                resourceProvider.getString(R.string.label_item_length),
                resourceProvider.getString(
                    R.string.item_body_format,
                    item.itemLength,
                    item.itemDimensionsUnit
                )
            ),
            ListItemViewModel(
                resourceProvider.getString(R.string.label_item_weight),
                resourceProvider.getString(
                    R.string.item_body_format,
                    item.itemWeight,
                    item.itemWeightUnit
                )
            )
        )
    }

    fun onFabClick() {
        val id = requireNotNull(item.value?.id)
        repository.addItemToCart(id)
            .flowOn(Dispatchers.IO)
            .onEach {
                // TODO
            }
            .catch {
                Timber.e(it)
            }
            .launchIn(viewModelScope)
    }
}
