package jp.cordea.drops.ui.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.item.databinding.ItemFragmentBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ItemFragment : Fragment(R.layout.item_fragment) {
    private val viewModel: ItemViewModel by viewModels()

    private val args: ItemFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ItemFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.init(args.item)

        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.recyclerView.adapter = adapter
        viewModel.items
            .onEach { list ->
                adapter.updateAsync(list.map { ListItem(it) })
            }
            .launchIn(lifecycleScope)
    }
}
