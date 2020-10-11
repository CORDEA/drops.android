package jp.cordea.drops.ui.inquiry

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.bindNavigationMenu
import jp.cordea.drops.ui.inquiry.databinding.InquiryFragmentBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InquiryFragment : Fragment(R.layout.inquiry_fragment) {
    private val viewModel: InquiryViewModel by viewModels()

    private lateinit var binding: InquiryFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = InquiryFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.toolbar.inflateNavigationView(R.layout.navigation_menu)
        binding.toolbar.bindNavigationMenu(viewModel)

        lifecycleScope.launch {
            for (event in viewModel.onEvent) {
                handleEvent(event)
            }
        }
    }

    private suspend fun handleEvent(event: InquiryViewModel.Event) {
        when (event) {
            InquiryViewModel.Event.NavigateToCatalog -> {
                binding.toolbar.collapse()
            }
            InquiryViewModel.Event.NavigateToHistory -> {
                binding.toolbar.collapse()
            }
            InquiryViewModel.Event.NavigateToAccount -> {
                binding.toolbar.collapse()
            }
            InquiryViewModel.Event.NavigateToInquiry -> binding.toolbar.collapse()
        }
    }
}
