package com.souza.marcus.pokedex.features.home.domain.mapper

import com.souza.marcus.pokedex.features.home.data.remote.model.PokemonListResponse
import com.souza.marcus.pokedex.features.home.domain.model.Pokemon

internal interface PokemonMapper {
    fun transform(source: PokemonListResponse): List<Pokemon>
}