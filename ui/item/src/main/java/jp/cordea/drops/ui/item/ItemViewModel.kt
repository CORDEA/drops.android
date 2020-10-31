package jp.cordea.drops.ui.item

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.cordea.drops.domain.Item
import jp.cordea.drops.ui.ResourceProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ItemViewModel @ViewModelInject constructor(
    private val resourceProvider: ResourceProvider
) : ViewModel() {
    private val _images = MutableStateFlow(emptyList<ImageItemViewModel>())
    val images: StateFlow<List<ImageItemViewModel>> get() = _images

    private val _items = MutableStateFlow(emptyList<ListItemViewModel>())
    val items: StateFlow<List<ListItemViewModel>> get() = _items

    val name = MutableLiveData("")
    val description = MutableLiveData("")

    fun init(item: Item) {
        name.value = item.name
        description.value = item.description
        _images.value = item.imageUrls.map { ImageItemViewModel(it) }
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
}
