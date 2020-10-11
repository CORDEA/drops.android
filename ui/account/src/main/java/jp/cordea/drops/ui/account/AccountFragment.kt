package jp.cordea.drops.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.account.databinding.AccountFragmentBinding
import jp.cordea.drops.ui.bindNavigationMenu
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.account_fragment) {
    @Inject
    lateinit var navigator: AccountNavigator

    private val viewModel: AccountViewModel by viewModels()

    private lateinit var binding: AccountFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AccountFragmentBinding.bind(view)
        binding.toolbar.inflateNavigationView(R.layout.navigation_menu)
        binding.toolbar.bindNavigationMenu(viewModel)
        binding.lifecycleOwner = viewLifecycleOwner

        lifecycleScope.launch {
            for (event in viewModel.onEvent) {
                handleEvent(event)
            }
        }
    }

    private suspend fun handleEvent(event: AccountViewModel.Event) {
        when (event) {
            AccountViewModel.Event.ClickedCatalog -> {
                binding.toolbar.collapse()
                navigator.navigateToCatalog()
            }
            AccountViewModel.Event.ClickedHistory -> {
                binding.toolbar.collapse()
                navigator.navigateToHistory()
            }
            AccountViewModel.Event.ClickedAccount -> binding.toolbar.collapse()
            AccountViewModel.Event.ClickedInquiry -> {
                binding.toolbar.collapse()
                navigator.navigateToInquiry()
            }
        }
    }
}
