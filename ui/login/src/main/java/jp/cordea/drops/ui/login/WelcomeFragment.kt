package jp.cordea.drops.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.login.WelcomeViewModel.Event
import jp.cordea.drops.ui.login.databinding.WelcomeFragmentBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragment : Fragment(R.layout.welcome_fragment) {
    @Inject
    lateinit var navigator: WelcomeNavigator

    private val viewModel: WelcomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            excludeTarget(R.id.button, true)
        }
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            excludeTarget(R.id.button, true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = WelcomeFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        lifecycleScope.launch {
            for (event in viewModel.onEvent) {
                when (event) {
                    is Event.NavigateToLogIn ->
                        navigator.navigateToLogIn(event.emailAddress)
                    is Event.NavigateToSignUp ->
                        navigator.navigateToSignUp(event.emailAddress)
                }
            }
        }
    }
}
