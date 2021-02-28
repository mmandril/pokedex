package com.souza.marcus.pokedex.features.home.presentation.viewstate

import com.souza.marcus.pokedex.features.home.domain.model.Pokemon

internal data class HomeViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isEmpty: Boolean = false,
    val pokemonList: List<Pokemon> = emptyList()
)