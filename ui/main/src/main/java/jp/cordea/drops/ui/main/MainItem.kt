package jp.cordea.drops.ui.main

import android.view.View
import coil.load
import coil.request.Disposable
import com.xwray.groupie.viewbinding.BindableItem
import com.xwray.groupie.viewbinding.GroupieViewHolder
import jp.cordea.drops.domain.Item
import jp.cordea.drops.ui.main.databinding.MainItemBinding
import com.xwray.groupie.Item as GroupieItem

class MainItemViewModel(
    val item: Item
) {
    val id = item.id
    val imageUrl = item.imageUrls.first()
}

class MainItem(
    private val viewModel: MainItemViewModel,
    private val listener: OnItemClickListener
) : BindableItem<MainItemBinding>() {
    interface OnItemClickListener {
        fun onItemClick(item: Item)
    }

    private var disposable: Disposable? = null

    override fun getLayout(): Int = R.layout.main_item

    override fun bind(viewBinding: MainItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
        disposable = viewBinding.imageView.load(viewModel.imageUrl)
        viewBinding.root.setOnClickListener { listener.onItemClick(viewModel.item) }
    }

    override fun unbind(viewHolder: GroupieViewHolder<MainItemBinding>) {
        disposable?.dispose()
        super.unbind(viewHolder)
    }

    override fun initializeViewBinding(view: View): MainItemBinding =
        MainItemBinding.bind(view)

    override fun isSameAs(other: GroupieItem<*>): Boolean =
        (other as? MainItem)?.viewModel?.id == viewModel.id

    override fun hasSameContentAs(other: GroupieItem<*>): Boolean =
        (other as? MainItem)?.viewModel == viewModel
}
