package jp.cordea.drops.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.bindNavigationMenu
import jp.cordea.drops.ui.history.databinding.HistoryFragmentBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.history_fragment) {
    @Inject
    lateinit var navigator: HistoryNavigator

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
            HistoryViewModel.Event.ClickedCatalog -> {
                binding.toolbar.collapse()
                navigator.navigateToCatalog()
            }
            HistoryViewModel.Event.ClickedHistory -> binding.toolbar.collapse()
            HistoryViewModel.Event.ClickedAccount -> {
                binding.toolbar.collapse()
                navigator.navigateToAccount()
            }
        }
    }
}
