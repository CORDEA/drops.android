package jp.cordea.drops.ui.account

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.account.databinding.AccountListItemBinding

class AccountListItemViewModel

class AccountListItem(
    private val viewModel: AccountListItemViewModel
) : BindableItem<AccountListItemBinding>() {
    override fun getLayout(): Int = R.layout.account_list_item

    override fun bind(viewBinding: AccountListItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): AccountListItemBinding =
        AccountListItemBinding.bind(view)
}
