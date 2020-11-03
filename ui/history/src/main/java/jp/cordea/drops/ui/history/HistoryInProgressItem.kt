package jp.cordea.drops.ui.history

import android.view.View
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.history.databinding.HistoryInProgressItemBinding

class HistoryInProgressItem(
    private val viewModel: HistoryInProgressItemViewModel
) : BindableItem<HistoryInProgressItemBinding>() {
    override fun getLayout(): Int = R.layout.history_in_progress_item

    override fun bind(viewBinding: HistoryInProgressItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): HistoryInProgressItemBinding =
        HistoryInProgressItemBinding.bind(view)

    override fun isSameAs(other: Item<*>): Boolean =
        (other as? HistoryInProgressItem)?.viewModel?.id == viewModel.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as? HistoryInProgressItem)?.viewModel == viewModel
}
