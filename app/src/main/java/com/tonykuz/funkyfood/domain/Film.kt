package com.tonykuz.funkyfood.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val title: String,
    val poster: Int,
    val instructions: String,
    var isInFavorites: Boolean = false
) : Parcelable