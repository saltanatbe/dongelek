package com.sm.dongelek.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import com.sm.dongelek.R
import com.sm.dongelek.databinding.MainFragmentBinding
import com.sm.dongelek.utils.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainFragment: BindingFragment<MainFragmentBinding>(MainFragmentBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // My changes here
        binding.run {

            val gagsAdapter = GagsAdapter { post->
                findNavController().navigate(R.id.to_post, bundleOf("gag_item" to post))
            }

            rv.adapter = gagsAdapter

            srl.setOnRefreshListener {
                viewModel.load()

            }

            viewModel.gags.observe(viewLifecycleOwner) {
                gagsAdapter.submitList(it)
            }
            viewModel.loadingGags.observe(viewLifecycleOwner) {
                srl.isRefreshing = it
            }

        }
    }

}