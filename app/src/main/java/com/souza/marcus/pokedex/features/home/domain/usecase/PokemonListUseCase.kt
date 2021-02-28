package com.souza.marcus.pokedex.features.home.domain.usecase

import com.souza.marcus.pokedex.features.home.domain.model.Pokemon
import com.souza.marcus.pokedex.features.home.domain.repository.PokeDexRepository
import io.reactivex.rxjava3.core.Single

internal class PokemonListUseCase(
    private val repository: PokeDexRepository
) {
    operator fun invoke(limit: Int = 20, offset: Int = 0): Single<List<Pokemon>> {
        return repository.getPokemonList(limit, offset)
    }
}