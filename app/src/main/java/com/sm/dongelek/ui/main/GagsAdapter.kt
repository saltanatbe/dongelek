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
        private val getSharedPreference: (Gag) -> Boolean
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
            cb.isChecked = getSharedPreference.invoke(item)
            //item.favNum = if (cb.isChecked) (item.favNum + 1) else (item.favNum)
            if (item.favNum!=0) {
                cb.text = item.favNum.toString()
            } else {
                cb.text = ""
            }

            cb.setOnCheckedChangeListener { _, _ ->
                saveIntoSharedPreference.invoke(item, cb)
                item.favNum = if (cb.isChecked) (item.favNum + 1) else (item.favNum - 1)
                if (item.favNum!=0) {
                    cb.text = item.favNum.toString()
                } else {
                    cb.text = ""
                }
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