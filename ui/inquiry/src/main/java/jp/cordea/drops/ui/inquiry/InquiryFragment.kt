package jp.cordea.drops.ui.inquiry

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.drops.ui.inquiry.databinding.InquiryFragmentBinding

@AndroidEntryPoint
class InquiryFragment : Fragment(R.layout.inquiry_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = InquiryFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
    }
}
