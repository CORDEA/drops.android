package jp.cordea.drops.ui.history

import android.view.View
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.history.databinding.HistoryItemBinding

class HistoryItemViewModel(
    val id: String,
    val imageUrl: String,
    val title: String,
    val body: String
)

class HistoryItem(
    private val viewModel: HistoryItemViewModel
) : BindableItem<HistoryItemBinding>() {
    override fun getLayout(): Int = R.layout.history_item

    override fun bind(viewBinding: HistoryItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): HistoryItemBinding =
        HistoryItemBinding.bind(view)

    override fun isSameAs(other: Item<*>): Boolean =
        (other as? HistoryItem)?.viewModel?.id == viewModel.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as? HistoryItem)?.viewModel == viewModel
}
