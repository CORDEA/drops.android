package jp.cordea.drops.ui.history

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.history.databinding.HistoryStatusItemBinding

class HistoryStatusItemViewModel

class HistoryStatusItem(
    private val viewModel: HistoryStatusItemViewModel
) : BindableItem<HistoryStatusItemBinding>() {
    override fun getLayout(): Int = R.layout.history_status_item

    override fun bind(viewBinding: HistoryStatusItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): HistoryStatusItemBinding =
        HistoryStatusItemBinding.bind(view)
}
