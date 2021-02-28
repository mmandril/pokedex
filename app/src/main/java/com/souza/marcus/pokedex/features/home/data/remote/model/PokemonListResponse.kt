package com.souza.marcus.pokedex.features.home.data.remote.model

import com.squareup.moshi.Json

private const val BASE_IMAGE_URL = "https://pokeres.bastionbot.org/images/pokemon/"

internal data class PokemonListResponse(
    @field:Json(name = "results") val results: List<PokemonResponse>?
)

internal data class PokemonResponse(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
) {
    fun getUrlImage(): String {
        val pokemonId = url.split("/".toRegex()).dropLast(1).last()
        return "$BASE_IMAGE_URL$pokemonId.png"
    }
}