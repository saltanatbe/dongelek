package com.sm.dongelek.ui.main

import android.content.Context
import android.content.Context.MODE_MULTI_PROCESS
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.sm.dongelek.R
import com.sm.dongelek.databinding.TextFragmentBinding
import com.sm.dongelek.utils.BindingFragment

class TextFragment : BindingFragment<TextFragmentBinding> (TextFragmentBinding :: inflate){
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.run {

            val gagsAdapter = GagsAdapter({ post ->
                activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.to_post, bundleOf("gag_item" to post))
            }, { item ->
                val shareIntent = Intent.createChooser(Intent().apply {
                    action = Intent.ACTION_SEND
                    if (!item.text.isNullOrEmpty()) {
                        putExtra(Intent.EXTRA_TEXT, Html.fromHtml(item.text))
                        type = "text/plain"
                    }
                }, null)
                requireActivity().startActivity(shareIntent)
            }, { item, cb ->
                val sp: SharedPreferences = requireContext().getSharedPreferences("likes", MODE_PRIVATE)
                val editor = sp.edit()
                editor.putBoolean(item.text+item.image, cb.isChecked)
                if (cb.isChecked){
                    editor.putInt("likes"+item.text+item.image, sp.getInt("likes"+item.text+item.image, 0)+1)
                } else {
                    editor.putInt("likes"+item.text+item.image, sp.getInt("likes"+item.text+item.image, 0)-1)
                }
                editor.apply()
            }, { item ->
                val sp: SharedPreferences = requireContext().getSharedPreferences("likes", MODE_PRIVATE)
                return@GagsAdapter sp.getBoolean(item.text+item.image, false)
            }, { item ->
                val sp: SharedPreferences = requireContext().getSharedPreferences("likes", MODE_PRIVATE)
                return@GagsAdapter sp.getInt("likes"+item.text+item.image, 0)
            })

            rv.adapter = gagsAdapter

            srl.setOnRefreshListener {
                viewModel.load()
            }

            viewModel.textGags.observe(viewLifecycleOwner) {
                gagsAdapter.submitList(it)
            }

            viewModel.loadingGags.observe(viewLifecycleOwner) {
                srl.isRefreshing = it
            }

        }
    }
}