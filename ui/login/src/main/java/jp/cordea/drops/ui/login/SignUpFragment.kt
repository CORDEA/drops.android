package jp.cordea.drops.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.login.SignUpViewModel.Event
import jp.cordea.drops.ui.login.databinding.SignUpFragmentBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.sign_up_fragment) {
    private val viewModel: SignUpViewModel by viewModels()

    private val args: SignUpFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            excludeTarget(R.id.button, true)
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            excludeTarget(R.id.button, true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = SignUpFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.init(args.emailAddress)

        lifecycleScope.launch {
            for (event in viewModel.onEvent) {
                when (event) {
                    Event.NavigateToMain -> findNavController().navigate(
                        SignUpFragmentDirections
                            .actionSignUpFragmentToMainFragment()
                    )
                }
            }
        }
    }
}
