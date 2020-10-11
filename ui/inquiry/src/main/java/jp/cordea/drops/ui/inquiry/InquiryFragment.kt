package jp.cordea.drops.ui.inquiry

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.bindNavigationMenu
import jp.cordea.drops.ui.inquiry.databinding.InquiryFragmentBinding

@AndroidEntryPoint
class InquiryFragment : Fragment(R.layout.inquiry_fragment) {
    private val viewModel: InquiryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = InquiryFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.toolbar.setNavigationView(R.layout.navigation_menu)
        binding.toolbar.bindNavigationMenu(viewModel)
    }
}
