package com.sm.dongelek.ui.main

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.sm.dinio.utils.binding.BindingRvAdapter
import com.sm.dongelek.databinding.GagItemBinding
import com.sm.dongelek.utils.ImageUtils
import com.sm.dongelek.utils.loadUrl
import kotlin.coroutines.coroutineContext

class GagsAdapter(
    private val onClick: (Gag) -> Unit
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
            cb.setChecked(item.isFav)
            if (item.favNum!=0) {
                cb.text = item.favNum.toString()
            } else {
                cb.text = ""
            }

            cb.setOnCheckedChangeListener { _, isClicked ->
                item.isFav = isClicked
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
                it.context.startActivity(shareIntent)
            }

            root.setOnClickListener {
                onClick.invoke(item)
            }

        }
    }
    


}