package jp.cordea.drops.ui.main

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.main.databinding.MainItemBinding

class MainItemViewModel

class MainItem(
    private val viewModel: MainItemViewModel
) : BindableItem<MainItemBinding>() {
    override fun getLayout(): Int = R.layout.main_item

    override fun bind(viewBinding: MainItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): MainItemBinding =
        MainItemBinding.bind(view)
}
