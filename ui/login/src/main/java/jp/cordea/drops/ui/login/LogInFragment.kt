package jp.cordea.drops.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.login.LogInViewModel.Event
import jp.cordea.drops.ui.login.databinding.LogInFragmentBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LogInFragment : Fragment(R.layout.log_in_fragment) {
    @Inject
    lateinit var navigator: LogInNavigator

    private val viewModel: LogInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = LogInFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        lifecycleScope.launch {
            for (event in viewModel.onEvent) {
                when (event) {
                    Event.NavigateToMain -> navigator.navigateToMain()
                }
            }
        }
    }
}
