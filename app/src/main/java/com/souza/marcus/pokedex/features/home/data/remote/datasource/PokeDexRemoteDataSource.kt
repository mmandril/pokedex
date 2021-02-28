package com.souza.marcus.pokedex.features.home.data.remote.datasource

import com.souza.marcus.pokedex.features.home.data.remote.model.PokemonListResponse
import io.reactivex.rxjava3.core.Single

internal interface PokeDexRemoteDataSource {
    fun getPokemonList(
        limit: Int,
        offset: Int
    ): Single<PokemonListResponse>
}