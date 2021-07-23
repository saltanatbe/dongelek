package com.sm.dongelek.ui.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gag(
        val text: String?,
        val image: String?,
        var favNum: Int
) : Parcelable
