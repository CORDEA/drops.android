package jp.cordea.drops.ui.cart

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import jp.cordea.drops.ui.cart.databinding.CartItemBinding

class CartItemViewModel

class CartItem(
    private val viewModel: CartItemViewModel
) : BindableItem<CartItemBinding>() {
    override fun getLayout(): Int = R.layout.cart_item

    override fun bind(viewBinding: CartItemBinding, position: Int) {
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): CartItemBinding =
        CartItemBinding.bind(view)
}
