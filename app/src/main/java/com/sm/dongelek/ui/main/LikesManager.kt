package com.sm.dongelek.ui.main

object LikesManager {

    private val likes = mutableSetOf<String>()

    private fun getElement(item: Gag): String {
        return (item.text ?: "") + (item.image ?: "")
    }

    fun like(enabled: Boolean, item: Gag) {
        val element = getElement(item)
        if (enabled) {
            likes.add(element)
        } else {
            likes.remove(element)
        }
    }

    fun isLiked(item: Gag): Boolean {
        val element = getElement(item)
        return likes.contains(element)
    }

}