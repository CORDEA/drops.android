package jp.cordea.drops.ui.main

import android.view.View
import coil.load
import coil.request.Disposable
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem
import com.xwray.groupie.viewbinding.GroupieViewHolder
import jp.cordea.drops.ui.main.databinding.MainItemBinding

class MainItemViewModel(
    val id: String,
    val name: String,
    val imageUrl: String
)

class MainItem(
    private val viewModel: MainItemViewModel,
    private val listener: OnItemClickListener
) : BindableItem<MainItemBinding>() {
    interface OnItemClickListener {
        fun onItemClick(id: String)
    }

    private var disposable: Disposable? = null

    override fun getLayout(): Int = R.layout.main_item

    override fun bind(viewBinding: MainItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
        disposable = viewBinding.imageView.load(viewModel.imageUrl)
        viewBinding.root.setOnClickListener { listener.onItemClick(viewModel.id) }
    }

    override fun unbind(viewHolder: GroupieViewHolder<MainItemBinding>) {
        disposable?.dispose()
        super.unbind(viewHolder)
    }

    override fun initializeViewBinding(view: View): MainItemBinding =
        MainItemBinding.bind(view)

    override fun isSameAs(other: Item<*>): Boolean =
        (other as? MainItem)?.viewModel?.id == viewModel.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as? MainItem)?.viewModel == viewModel
}
