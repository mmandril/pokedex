package com.souza.marcus.pokedex.features.home.data.remote.api

import com.souza.marcus.pokedex.features.home.data.remote.model.PokemonListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PokeDexService {
    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<PokemonListResponse>
}