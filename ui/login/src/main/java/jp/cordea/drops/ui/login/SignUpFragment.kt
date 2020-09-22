package jp.cordea.drops.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.login.databinding.SignUpFragmentBinding
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.sign_up_fragment) {
//    @Inject
//    lateinit var navigator: Navigator

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = SignUpFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }
}
