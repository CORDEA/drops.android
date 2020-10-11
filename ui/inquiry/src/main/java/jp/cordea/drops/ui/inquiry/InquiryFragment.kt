package jp.cordea.drops.ui.inquiry

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.bindNavigationMenu
import jp.cordea.drops.ui.inquiry.databinding.InquiryFragmentBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class InquiryFragment : Fragment(R.layout.inquiry_fragment) {
    @Inject
    lateinit var navigator: InquiryNavigator

    private val viewModel: InquiryViewModel by viewModels()

    private lateinit var binding: InquiryFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = InquiryFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.toolbar.inflateNavigationView(R.layout.navigation_menu)
        binding.toolbar.bindNavigationMenu(viewModel)
        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.recyclerView.adapter = adapter

        viewModel.onEvent
            .onEach { handleEvent(it) }
            .launchIn(lifecycleScope)

        viewModel.items
            .onEach { items -> adapter.updateAsync(items.map { InquiryItem(it) }) }
            .launchIn(lifecycleScope)
    }

    private suspend fun handleEvent(event: InquiryViewModel.Event) {
        when (event) {
            InquiryViewModel.Event.NavigateToCatalog -> {
                binding.toolbar.collapse()
                navigator.navigateToCatalog()
            }
            InquiryViewModel.Event.NavigateToHistory -> {
                binding.toolbar.collapse()
                navigator.navigateToHistory()
            }
            InquiryViewModel.Event.NavigateToAccount -> {
                binding.toolbar.collapse()
                navigator.navigateToAccount()
            }
            InquiryViewModel.Event.NavigateToInquiry -> binding.toolbar.collapse()
        }
    }
}
