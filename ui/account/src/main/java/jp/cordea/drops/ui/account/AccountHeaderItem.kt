package jp.cordea.drops.ui.account

import android.view.View
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.account.databinding.AccountHeaderItemBinding

class AccountHeaderItem(
    private val viewModel: AccountHeaderItemViewModel
) : BindableItem<AccountHeaderItemBinding>() {
    override fun getLayout(): Int = R.layout.account_header_item

    override fun bind(viewBinding: AccountHeaderItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): AccountHeaderItemBinding =
        AccountHeaderItemBinding.bind(view)

    override fun isSameAs(other: Item<*>): Boolean =
        (other as? AccountHeaderItem)?.viewModel == viewModel

    override fun hasSameContentAs(other: Item<*>): Boolean = isSameAs(other)
}
