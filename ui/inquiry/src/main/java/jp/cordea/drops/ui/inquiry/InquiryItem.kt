package jp.cordea.drops.ui.inquiry

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.inquiry.databinding.InquiryItemBinding

data class InquiryItemViewModel(
    val id: Int,
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

class InquiryItem(
    private val viewModel: InquiryItemViewModel
) : BindableItem<InquiryItemBinding>() {
    override fun getLayout(): Int = R.layout.inquiry_item

    override fun bind(viewBinding: InquiryItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): InquiryItemBinding =
        InquiryItemBinding.bind(view)

    override fun isSameAs(other: Item<*>): Boolean =
        (other as? InquiryItem)?.viewModel?.id == viewModel.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as? InquiryItem)?.viewModel == viewModel
}
