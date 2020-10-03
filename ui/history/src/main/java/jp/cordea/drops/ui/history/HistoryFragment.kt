package jp.cordea.drops.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.history.databinding.HistoryFragmentBinding

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.history_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = HistoryFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
    }
}
