package jp.cordea.drops.ui.item

import android.view.View
import coil.load
import coil.request.Disposable
import com.xwray.groupie.viewbinding.BindableItem
import com.xwray.groupie.viewbinding.GroupieViewHolder
import jp.cordea.drops.ui.item.databinding.ImageItemBinding

data class ImageItemViewModel(
    val imageUrl: String
)

class ImageItem(private val viewModel: ImageItemViewModel) : BindableItem<ImageItemBinding>() {
    private var disposable: Disposable? = null

    override fun getLayout(): Int = R.layout.image_item

    override fun bind(viewBinding: ImageItemBinding, position: Int) {
        disposable = viewBinding.imageView.load(viewModel.imageUrl)
    }

    override fun unbind(viewHolder: GroupieViewHolder<ImageItemBinding>) {
        disposable?.dispose()
        super.unbind(viewHolder)
    }

    override fun initializeViewBinding(view: View): ImageItemBinding =
        ImageItemBinding.bind(view)
}
