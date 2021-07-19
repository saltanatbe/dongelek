package com.sm.dongelek.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.sm.dongelek.R
import com.stfalcon.imageviewer.StfalconImageViewer


interface ImageUtils {

    fun loadImage(context: Context, images: List<String>?, index: Int = 0)

    companion object : ImageUtils {

        private fun getCircularProgressDrawable(context: Context): Drawable {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.setColorSchemeColors(context.resources.getColor(R.color.purple_200))
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            return circularProgressDrawable
        }

        override fun loadImage(context: Context, images: List<String>?, index: Int){
            StfalconImageViewer.Builder(context, images) { view, image ->
                view.loadUrl(image, getCircularProgressDrawable(view.context))
            }.withHiddenStatusBar(false).withStartPosition(index).show()
        }
    }
}
