package com.souza.marcus.pokedex.features.home.domain.repository

import com.souza.marcus.pokedex.features.home.domain.model.Pokemon
import io.reactivex.rxjava3.core.Single

internal interface PokeDexRepository {

    fun getPokemonList(
        limit: Int,
        offset: Int
    ): Single<List<Pokemon>>
}