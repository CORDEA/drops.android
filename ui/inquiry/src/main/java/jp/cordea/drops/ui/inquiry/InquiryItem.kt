package jp.cordea.drops.ui.inquiry

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.inquiry.databinding.InquiryItemBinding

class InquiryItemViewModel

class InquiryItem(
    private val viewModel: InquiryItemViewModel
) : BindableItem<InquiryItemBinding>() {
    override fun getLayout(): Int = R.layout.inquiry_item

    override fun bind(viewBinding: InquiryItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): InquiryItemBinding =
        InquiryItemBinding.bind(view)
}
