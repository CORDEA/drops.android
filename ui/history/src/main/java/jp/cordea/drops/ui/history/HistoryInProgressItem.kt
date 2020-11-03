package jp.cordea.drops.ui.history

import android.view.View
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.history.databinding.HistoryStatusItemBinding

class HistoryInProgressItem(
    private val viewModel: HistoryInProgressItemViewModel
) : BindableItem<HistoryStatusItemBinding>() {
    override fun getLayout(): Int = R.layout.history_status_item

    override fun bind(viewBinding: HistoryStatusItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): HistoryStatusItemBinding =
        HistoryStatusItemBinding.bind(view)

    override fun isSameAs(other: Item<*>): Boolean =
        (other as? HistoryInProgressItem)?.viewModel?.id == viewModel.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as? HistoryInProgressItem)?.viewModel == viewModel
}
