package jp.cordea.drops.ui.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import jp.cordea.drops.ui.cart.databinding.CartFragmentBinding

class CartFragment : Fragment(R.layout.cart_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CartFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
    }
}
