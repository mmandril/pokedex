package com.souza.marcus.pokedex.features.home.data.repository

import com.souza.marcus.pokedex.features.home.data.remote.datasource.PokeDexRemoteDataSource
import com.souza.marcus.pokedex.features.home.domain.mapper.PokemonMapper
import com.souza.marcus.pokedex.features.home.domain.model.Pokemon
import com.souza.marcus.pokedex.features.home.domain.repository.PokeDexRepository
import io.reactivex.rxjava3.core.Single

internal class PokeDexRepositoryImpl(
    private val remoteDataSource: PokeDexRemoteDataSource,
    private val pokemonMapper: PokemonMapper
) : PokeDexRepository {
    override fun getPokemonList(limit: Int, offset: Int): Single<List<Pokemon>> {
        return remoteDataSource.getPokemonList(limit, offset).map(pokemonMapper::transform)
    }
}