package jp.cordea.drops.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.bindNavigationMenu
import jp.cordea.drops.ui.history.databinding.HistoryFragmentBinding
import jp.cordea.drops.ui.navigation.NavGraphDirections
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.history_fragment) {
    private val viewModel: HistoryViewModel by viewModels()

    private lateinit var binding: HistoryFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HistoryFragmentBinding.bind(view)
        binding.toolbar.inflateNavigationView(R.layout.navigation_menu)
        binding.toolbar.bindNavigationMenu(viewModel)
        binding.lifecycleOwner = viewLifecycleOwner

        lifecycleScope.launch {
            for (event in viewModel.onEvent) {
                handleEvent(event)
            }
        }
    }

    private suspend fun handleEvent(event: HistoryViewModel.Event) {
        when (event) {
            HistoryViewModel.Event.NavigateToCatalog -> {
                binding.toolbar.collapse()
                findNavController()
                    .navigate(NavGraphDirections.actionGlobalMainFragment())
            }
            HistoryViewModel.Event.NavigateToHistory -> binding.toolbar.collapse()
            HistoryViewModel.Event.NavigateToAccount -> {
                binding.toolbar.collapse()
                findNavController()
                    .navigate(NavGraphDirections.actionGlobalAccountFragment())
            }
            HistoryViewModel.Event.NavigateToInquiry -> {
                binding.toolbar.collapse()
                findNavController()
                    .navigate(NavGraphDirections.actionGlobalInquiryFragment())
            }
        }
    }
}
