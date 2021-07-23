package com.sm.dongelek.ui.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run{

            val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
            vp2.adapter = adapter

            TabLayoutMediator(tabs, vp2){tab, position->
                when (position){
                    0->tab.text="Text"
                    1->tab.text="IMAGE"
                }
            }.attach()
            }

        // My changes here
        /*binding.run {

            val gagsAdapter = GagsAdapter ({ post->
                findNavController().navigate(R.id.to_post, bundleOf("gag_item" to post))
            }, { item ->
                val shareIntent = Intent.createChooser(Intent().apply {
                    action = Intent.ACTION_SEND
                    if (!item.text.isNullOrEmpty()){
                        putExtra(Intent.EXTRA_TEXT, Html.fromHtml(item.text))
                        type = "text/plain"
                    }
                    if (!item.image.isNullOrEmpty()){
                        putExtra(Intent.EXTRA_TEXT, item.image)
                        type = "text/plain"
                    }
                }, null)
                requireActivity().startActivity(shareIntent)
            })

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

        }*/
    }

}