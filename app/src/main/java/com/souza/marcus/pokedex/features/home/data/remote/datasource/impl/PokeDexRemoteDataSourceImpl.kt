package com.souza.marcus.pokedex.features.home.data.remote.datasource.impl

import com.souza.marcus.pokedex.features.home.data.remote.api.PokeDexService
import com.souza.marcus.pokedex.features.home.data.remote.datasource.PokeDexRemoteDataSource
import com.souza.marcus.pokedex.features.home.data.remote.model.PokemonListResponse
import io.reactivex.rxjava3.core.Single

internal class PokeDexRemoteDataSourceImpl(
    private val pokeDexService: PokeDexService
) : PokeDexRemoteDataSource {
    override fun getPokemonList(limit: Int, offset: Int): Single<PokemonListResponse> {
        return pokeDexService.getPokemonList(limit, offset)
    }
}