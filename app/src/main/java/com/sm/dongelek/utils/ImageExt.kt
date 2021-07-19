package com.sm.dongelek.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sm.dongelek.R


fun ImageView.loadUrl(url: String?, @DrawableRes placeholder: Int = R.drawable.bg_image_placeholder) {
    if (url.isNullOrEmpty()) {
        loadDrawable(placeholder)
    } else {
        Glide.with(this)
            .load(url)
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}

fun ImageView.loadUrl(url: String?, placeholder: Drawable) {
    if (url.isNullOrEmpty()) {
//        loadDrawable(placeholder)
    } else {
        Glide.with(this)
                .load(url)
                .placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(this)
    }
}

fun ImageView.loadDrawable(@DrawableRes resId: Int) {
    Glide.with(this)
        .load(resId)
        .into(this)
}