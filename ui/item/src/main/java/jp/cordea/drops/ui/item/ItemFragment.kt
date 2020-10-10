package jp.cordea.drops.ui.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import jp.cordea.drops.ui.item.databinding.ItemFragmentBinding

class ItemFragment : Fragment(R.layout.item_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ItemFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
    }
}
