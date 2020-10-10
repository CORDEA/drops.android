package jp.cordea.drops.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.bindNavigationMenu
import jp.cordea.drops.ui.main.databinding.MainFragmentBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    @Inject
    lateinit var navigator: MainNavigator

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = ContextThemeWrapper(requireContext(), R.style.AppTheme_NoActionBar)
        requireActivity().window.statusBarColor =
            requireContext().getColor(R.color.colorPrimaryDark)
        return inflater.cloneInContext(context).inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        binding.toolbar.setNavigationView(R.layout.navigation_menu)
        binding.toolbar.setMenu(R.drawable.ic_baseline_filter_list_24, R.layout.main_filter_menu)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.toolbar.bindNavigationMenu(viewModel)

        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(MainItemDecoration(requireContext()))
        binding.recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 3, GridLayoutManager.HORIZONTAL, false)

        lifecycleScope.launch {
            for (event in viewModel.onEvent) {
                handleEvent(event)
            }
        }
    }

    private suspend fun handleEvent(event: MainViewModel.Event) {
        when (event) {
            MainViewModel.Event.NavigateToCatalog -> binding.toolbar.collapse()
            MainViewModel.Event.NavigateToHistory -> {
                binding.toolbar.collapse()
                navigator.navigateToHistory()
            }
            MainViewModel.Event.NavigateToAccount -> {
                binding.toolbar.collapse()
                navigator.navigateToAccount()
            }
        }
    }
}
