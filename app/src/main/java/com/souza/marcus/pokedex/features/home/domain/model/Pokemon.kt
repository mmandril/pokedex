package com.souza.marcus.pokedex.features.home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class Pokemon(
    val name: String,
    val imageUrl: String
) : Parcelable