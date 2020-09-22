package jp.cordea.drops.ui.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.sign_up_fragment) {
    private val viewModel: WelcomeViewModel by viewModels()
}
