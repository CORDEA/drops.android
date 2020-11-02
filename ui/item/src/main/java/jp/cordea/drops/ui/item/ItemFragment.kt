package jp.cordea.drops.ui.item

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.item.databinding.ItemFragmentBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.math.absoluteValue

@AndroidEntryPoint
class ItemFragment : Fragment(R.layout.item_fragment) {
    private val viewModel: ItemViewModel by viewModels()

    private val args: ItemFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ItemFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.init(args.item)

        val imageAdapter = GroupAdapter<GroupieViewHolder>()
        binding.viewPager.adapter = imageAdapter
        binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.addItemDecoration(ImageItemDecoration())
        binding.viewPager.setPageTransformer { page, position ->
            page.translationX = page.width * 0.2f * -position
            page.scaleY = 1f - (position.absoluteValue * 0.1f)
        }
        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.recyclerView.adapter = adapter
        viewModel.items
            .onEach { list ->
                adapter.updateAsync(list.map { ListItem(it) })
            }
            .launchIn(lifecycleScope)
        viewModel.images
            .onEach { list ->
                imageAdapter.updateAsync(list.map { ImageItem(it) })
            }
            .launchIn(lifecycleScope)
        viewModel.tags
            .onEach { list ->
                binding.tags.isVisible = list.isNotEmpty()
                binding.tags.removeAllViews()
                list.forEach {
                    val chip = Chip(requireContext())
                    chip.text = it
                    chip.setChipBackgroundColorResource(R.color.colorPrimary)
                    chip.isClickable = false
                    binding.tags.addView(chip)
                }
            }
            .launchIn(lifecycleScope)
    }

    private class ImageItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val width = parent.width
            if (width <= 0) {
                return
            }
            val margin = (width * 0.1f).toInt()
            outRect.right = margin
            outRect.left = margin
        }
    }
}
