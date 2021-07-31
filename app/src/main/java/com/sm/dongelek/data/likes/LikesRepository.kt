package com.sm.dongelek.data.likes

import com.sm.dongelek.data.models.Gag

class LikesRepository(
    private val memoryDataSource: LikesDataSource,
    private val persistentDataSource: LikesDataSource,
    private val onItemChange: () -> Unit
): LikesDataSource {

    init {
        // Read persistent storage and move its data to memory
    }

    override fun like(enabled: Boolean, item: Gag) {
        // Check for not null data source
        // If we have faster data source, use that instead
        // If not use slowest
        // If none, create
        TODO("Not yet implemented")
        // TODO: Notify that update has happened
        onItemChange.invoke()
    }

    override fun isLiked(item: Gag): Boolean {
        TODO("Not yet implemented")
    }

}