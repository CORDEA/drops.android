package jp.cordea.drops.ui.main

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.main.databinding.MainItemBinding

class MainItemViewModel(
    val id: String
)

class MainItem(
    private val viewModel: MainItemViewModel,
    private val listener: OnItemClickListener
) : BindableItem<MainItemBinding>() {
    interface OnItemClickListener {
        fun onItemClick(id: String)
    }

    override fun getLayout(): Int = R.layout.main_item

    override fun bind(viewBinding: MainItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
        viewBinding.root.setOnClickListener { listener.onItemClick(viewModel.id) }
    }

    override fun initializeViewBinding(view: View): MainItemBinding =
        MainItemBinding.bind(view)
}
