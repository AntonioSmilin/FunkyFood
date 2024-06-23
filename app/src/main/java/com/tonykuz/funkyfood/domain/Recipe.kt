package com.tonykuz.funkyfood.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val title: String,
    val image: Int,
    val instructions: String,
    var isInFavorites: Boolean = false
) : Parcelable