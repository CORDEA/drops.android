package jp.cordea.drops.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.account.databinding.AccountFragmentBinding

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.account_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = AccountFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
    }
}
