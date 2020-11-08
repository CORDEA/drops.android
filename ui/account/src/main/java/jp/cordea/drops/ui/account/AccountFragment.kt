package jp.cordea.drops.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.account.databinding.AccountFragmentBinding
import jp.cordea.drops.ui.bindNavigationMenu
import jp.cordea.drops.ui.navigation.NavGraphDirections
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.account_fragment) {
    private val viewModel: AccountViewModel by viewModels()

    private lateinit var binding: AccountFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AccountFragmentBinding.bind(view)
        binding.toolbar.inflateNavigationView(R.layout.navigation_menu)
        binding.toolbar.bindNavigationMenu(viewModel)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            for (event in viewModel.onEvent) {
                handleEvent(event)
            }
        }

        viewModel.items
            .map { list ->
                list.map {
                    when (it) {
                        is AccountHeaderItemViewModel -> AccountHeaderItem(it)
                        is AccountListItemViewModel -> AccountListItem(it)
                    }
                }
            }
            .onEach { adapter.updateAsync(it) }
            .launchIn(lifecycleScope)
    }

    private suspend fun handleEvent(event: AccountViewModel.Event) {
        when (event) {
            AccountViewModel.Event.NavigateToCatalog -> {
                binding.toolbar.collapse()
                findNavController()
                    .navigate(NavGraphDirections.actionGlobalMainFragment())
            }
            AccountViewModel.Event.NavigateToHistory -> {
                binding.toolbar.collapse()
                findNavController()
                    .navigate(NavGraphDirections.actionGlobalHistoryFragment())
            }
            AccountViewModel.Event.NavigateToAccount -> binding.toolbar.collapse()
            AccountViewModel.Event.NavigateToInquiry -> {
                binding.toolbar.collapse()
                findNavController()
                    .navigate(NavGraphDirections.actionGlobalInquiryFragment())
            }
        }
    }
}
