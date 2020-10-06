package jp.cordea.drops.ui.account

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.account.databinding.AccountItemBinding

class AccountItemViewModel

class AccountItem(
    private val viewModel: AccountItemViewModel
) : BindableItem<AccountItemBinding>() {
    override fun getLayout(): Int = R.layout.account_item

    override fun bind(viewBinding: AccountItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): AccountItemBinding =
        AccountItemBinding.bind(view)
}
