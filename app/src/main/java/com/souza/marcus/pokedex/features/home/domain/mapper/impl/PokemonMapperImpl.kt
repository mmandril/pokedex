package com.souza.marcus.pokedex.features.home.domain.mapper.impl

import com.souza.marcus.pokedex.features.home.data.remote.model.PokemonListResponse
import com.souza.marcus.pokedex.features.home.data.remote.model.PokemonResponse
import com.souza.marcus.pokedex.features.home.domain.mapper.PokemonMapper
import com.souza.marcus.pokedex.features.home.domain.model.Pokemon

internal class PokemonMapperImpl : PokemonMapper {
    override fun transform(source: PokemonListResponse): List<Pokemon> {
        return source.results?.map {
            mapPokemon(it)
        } ?: emptyList()
    }

    private fun mapPokemon(pokemonResponse: PokemonResponse): Pokemon {
        return Pokemon(
            name = pokemonResponse.name,
            imageUrl = pokemonResponse.getUrlImage()
        )
    }
}