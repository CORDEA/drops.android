package jp.cordea.drops.ui.history

import android.view.View
import androidx.core.view.isVisible
import coil.load
import coil.request.Disposable
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem
import com.xwray.groupie.viewbinding.GroupieViewHolder
import jp.cordea.drops.ui.history.databinding.HistoryCompletedItemBinding

class HistoryCompletedItem(
    private val viewModel: HistoryCompletedItemViewModel
) : BindableItem<HistoryCompletedItemBinding>() {
    private val disposables = mutableListOf<Disposable>()

    override fun getLayout(): Int = R.layout.history_completed_item

    override fun bind(viewBinding: HistoryCompletedItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
        val imageUrls = viewModel.imageUrls
        viewBinding.imageView1.isVisible = imageUrls.isNotEmpty()
        viewBinding.imageView2.isVisible = imageUrls.size > 1
        when (imageUrls.size) {
            0 ->
                // TODO
                emptyList()
            1 -> listOf(viewBinding.imageView1.load(imageUrls[0]))
            else -> listOf(
                viewBinding.imageView1.load(imageUrls[0]),
                viewBinding.imageView2.load(imageUrls[1])
            )
        }.let { disposables.addAll(it) }
    }

    override fun unbind(viewHolder: GroupieViewHolder<HistoryCompletedItemBinding>) {
        disposables.forEach { it.dispose() }
        disposables.clear()
        super.unbind(viewHolder)
    }

    override fun initializeViewBinding(view: View): HistoryCompletedItemBinding =
        HistoryCompletedItemBinding.bind(view)

    override fun isSameAs(other: Item<*>): Boolean =
        (other as? HistoryCompletedItem)?.viewModel?.id == viewModel.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as? HistoryCompletedItem)?.viewModel == viewModel
}
