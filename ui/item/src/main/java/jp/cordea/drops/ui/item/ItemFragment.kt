package jp.cordea.drops.ui.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.item.databinding.ItemFragmentBinding

@AndroidEntryPoint
class ItemFragment : Fragment(R.layout.item_fragment) {
    private val viewModel: ItemViewModel by viewModels()

    private val args: ItemFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ItemFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.init(args.item)
    }
}
