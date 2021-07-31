package com.sm.dongelek.data.likes

import android.content.SharedPreferences
import com.sm.dongelek.data.models.Gag

class LikesPersistentDataSource (
    private val sp: SharedPreferences
): LikesDataSource {

    private fun getElement(item: Gag): String {
        return (item.text ?: "") + (item.image ?: "")
    }

    override fun like(enabled: Boolean, item: Gag) {
        val editor = sp.edit()
        editor.putBoolean(getElement(item), enabled)
        editor.apply()
    }

    override fun isLiked(item: Gag): Boolean {
        return sp.getBoolean(getElement(item), false)
    }
}