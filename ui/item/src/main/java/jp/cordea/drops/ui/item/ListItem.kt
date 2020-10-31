package jp.cordea.drops.ui.item

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.item.databinding.ListItemBinding

data class ListItemViewModel(
    val label: String,
    val body: String
)

class ListItem(private val viewModel: ListItemViewModel) : BindableItem<ListItemBinding>() {
    override fun getLayout(): Int = R.layout.list_item

    override fun bind(viewBinding: ListItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): ListItemBinding =
        ListItemBinding.bind(view)
}
