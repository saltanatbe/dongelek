package com.sm.dongelek.data.likes

import com.sm.dongelek.data.models.Gag

interface LikesDataSource {

    fun like(enabled: Boolean, item: Gag)

    fun isLiked(item: Gag): Boolean

}