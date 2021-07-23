package com.sm.dongelek.ui.main

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.sm.dongelek.R
import com.sm.dongelek.databinding.FragmentPostDetailsBinding
import com.sm.dongelek.utils.BindingFragment
import com.sm.dongelek.utils.ImageUtils
import com.sm.dongelek.utils.loadUrl
import com.stfalcon.imageviewer.StfalconImageViewer

class PostDetailsFragment: BindingFragment<FragmentPostDetailsBinding>(FragmentPostDetailsBinding::inflate)  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            val gag = arguments?.getParcelable("gag_item") as? Gag

            toolbar.setNavigationOnClickListener{
                findNavController().navigateUp()
            }

            gag?.let{
                tvPostContent.visibility = if (it.text.isNullOrEmpty()) View.GONE else View.VISIBLE
                tvPostContent.text = Html.fromHtml(it.text ?: "")
                iv.loadUrl(it.image)
                iv.visibility = if (it.image.isNullOrEmpty()) View.GONE else View.VISIBLE
                cbFavourite.setOnCheckedChangeListener(null)
                val sp: SharedPreferences = requireContext().getSharedPreferences("likes", Context.MODE_PRIVATE)
                cbFavourite.isChecked = sp.getBoolean(it.text+it.image, false)
                if (it.favNum!=0) {
                    cbFavourite.text = it.favNum.toString()
                } else {
                    cbFavourite.text = ""
                }
            }


            cbFavourite.setOnCheckedChangeListener { _, _ ->
                val sp: SharedPreferences = requireContext().getSharedPreferences("likes", Context.MODE_PRIVATE)
                val editor = sp.edit()
                editor.putBoolean(gag?.text+gag?.image, cbFavourite.isChecked)
                editor.apply()
                gag?.favNum = if (cbFavourite.isChecked) (gag?.favNum!! + 1) else (gag?.favNum!! - 1)
                if (gag.favNum !=0) {
                    cbFavourite.text = gag.favNum.toString()
                } else {
                    cbFavourite.text = ""
                }
            }

            ibShare.setOnClickListener{
                val shareIntent: Intent = Intent.createChooser( Intent().apply{
                    action = Intent.ACTION_SEND
                    gag?.let{
                        if (!it.text.isNullOrEmpty()) {
                            putExtra(Intent.EXTRA_TEXT, Html.fromHtml(it.text))
                            type = "text/plain"
                        }
                        if (!it.image.isNullOrEmpty()){
                            putExtra(Intent.EXTRA_TEXT, it.image)
                            type = "text/plain"
                        }
                    }
                    //putExtra(Intent.EXTRA_TITLE,"TITLE" )
                                                                      }, null)
                /*val pi = PendingIntent.getBroadcast(context, requestCode,
                        Intent(context, MyBroadcastReceiver::class.java),
                        PendingIntent.FLAG_UPDATE_CURRENT)*/
                startActivity(shareIntent)
            }

            iv.setOnClickListener {

            }

            ibSendComment.setOnClickListener{
                val comment = etComment.text
                etComment.text.clear()
            }
        }
    }
}