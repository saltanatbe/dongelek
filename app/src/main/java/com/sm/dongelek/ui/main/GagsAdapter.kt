package com.sm.dongelek.ui.main

import android.content.SharedPreferences
import android.text.Html
import android.view.View
import android.widget.CheckBox
import com.sm.dinio.utils.binding.BindingRvAdapter
import com.sm.dongelek.databinding.GagItemBinding
import com.sm.dongelek.utils.ImageUtils
import com.sm.dongelek.utils.loadUrl

class GagsAdapter(
        private val onClick: (Gag) -> Unit,
        private val onShareClick: (Gag) -> Unit,
        private val saveIntoSharedPreference: (Gag, CheckBox) -> Unit,
        private val getSharedPreferenceCheckBox: (Gag) -> Boolean,
        private val getSharedPreferenceLikes: (Gag) -> Int
): BindingRvAdapter<Gag, GagItemBinding>(GagItemBinding::inflate) {


    override fun bind(item: Gag, binding: GagItemBinding) {


        binding.run {
            tvContent.visibility = if (item.text.isNullOrEmpty()) View.GONE else View.VISIBLE
            tvContent.text = Html.fromHtml(item.text ?: "")

            iv.loadUrl(item.image)
            iv.visibility = if (item.image.isNullOrEmpty()) View.GONE else View.VISIBLE
            iv.setOnClickListener {
                val images = items.mapNotNull {
                    it.image
                }
                ImageUtils.loadImage(root.context, images, images.indexOf(item.image))
            }

            cb.setOnCheckedChangeListener(null)

            cb.isChecked = getSharedPreferenceCheckBox.invoke(item)
            cb.text = getSharedPreferenceLikes.invoke(item).toString()

            cb.setOnCheckedChangeListener { _, _ ->
                saveIntoSharedPreference.invoke(item, cb)
                cb.text= getSharedPreferenceLikes.invoke(item).toString()
            }

            ibComment.setOnClickListener{
                onClick.invoke(item)
            }

            ibShare.setOnClickListener{
                onShareClick.invoke(item)
            }

            root.setOnClickListener {
                onClick.invoke(item)
            }

        }
    }


}