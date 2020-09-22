package jp.cordea.drops.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.Navigator
import jp.cordea.drops.ui.login.databinding.WelcomeFragmentBinding
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragment : Fragment(R.layout.welcome_fragment) {
    @Inject
    lateinit var navigator: Navigator

    private val viewModel: WelcomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = WelcomeFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }
}
