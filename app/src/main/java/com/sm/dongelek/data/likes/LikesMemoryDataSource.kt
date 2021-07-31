package com.sm.dongelek.data.likes

import com.sm.dongelek.data.models.Gag

class LikesMemoryDataSource: LikesDataSource {

    private val likes = mutableSetOf<String>()

    private fun getElement(item: Gag): String {
        return (item.text ?: "") + (item.image ?: "")
    }

    override fun like(enabled: Boolean, item: Gag) {
        val element = getElement(item)
        if (enabled) {
            likes.add(element)
        } else {
            likes.remove(element)
        }
    }

    override fun isLiked(item: Gag): Boolean {
        val element = getElement(item)
        return likes.contains(element)
    }
}