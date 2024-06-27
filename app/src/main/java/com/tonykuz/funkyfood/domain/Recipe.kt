package com.tonykuz.funkyfood.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val title: String?,
    val id: Int?,
    val image: String?,
    val instructions: String?,
    var isInFavorites: Boolean = false
) : Parcelable