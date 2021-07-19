package com.sm.dongelek.ui.pages

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.sm.dongelek.R
import com.sm.dongelek.databinding.PageFragmentBinding
import com.sm.dongelek.utils.BindingFragment

class PageFragment: BindingFragment<PageFragmentBinding>(PageFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            tv.text = arguments?.getString("id")

            val id = ((arguments?.getInt("n") ?: 0) + 1)

            btn.setOnClickListener {
                findNavController().navigate(R.id.to_profile, bundleOf("id" to "NEXT: ${id}", "n" to id))
            }
        }
    }

}