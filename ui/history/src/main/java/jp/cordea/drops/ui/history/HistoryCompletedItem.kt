package jp.cordea.drops.ui.history

import android.view.View
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.history.databinding.HistoryCompletedItemBinding

class HistoryCompletedItem(
    private val viewModel: HistoryCompletedItemViewModel
) : BindableItem<HistoryCompletedItemBinding>() {
    override fun getLayout(): Int = R.layout.history_completed_item

    override fun bind(viewBinding: HistoryCompletedItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): HistoryCompletedItemBinding =
        HistoryCompletedItemBinding.bind(view)

    override fun isSameAs(other: Item<*>): Boolean =
        (other as? HistoryCompletedItem)?.viewModel?.id == viewModel.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as? HistoryCompletedItem)?.viewModel == viewModel
}
