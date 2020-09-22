package jp.cordea.drops.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.main.databinding.MainFragmentBinding

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

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
        val binding = MainFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }
}
