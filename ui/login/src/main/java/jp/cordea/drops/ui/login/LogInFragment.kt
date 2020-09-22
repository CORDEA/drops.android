package jp.cordea.drops.ui.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : Fragment(R.layout.log_in_fragment) {
    private val viewModel: WelcomeViewModel by viewModels()
}
