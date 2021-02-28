package com.souza.marcus.pokedex.features.home.data.remote.datasource

import com.souza.marcus.pokedex.features.home.data.remote.api.PokeDexService
import com.souza.marcus.pokedex.features.home.data.remote.datasource.impl.PokeDexRemoteDataSourceImpl
import com.souza.marcus.pokedex.features.home.data.remote.model.PokemonListResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

internal class PokeDexRemoteDataSourceTest {

    @MockK
    lateinit var pokeDexService: PokeDexService

    private lateinit var pokeDexRemoteDataSource: PokeDexRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        pokeDexRemoteDataSource = PokeDexRemoteDataSourceImpl(pokeDexService)
    }

    @Test
    fun `getPokemonList should return PokemonListResponse with success`() {
        // Given
        val mockedPokemonListResponse = mockk<PokemonListResponse>()
        every { pokeDexService.getPokemonList(20, 0) } answers {
            Single.just(
                mockedPokemonListResponse
            )
        }
        // When
        val result = pokeDexRemoteDataSource.getPokemonList(20, 0).test()

        // Then
        result
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `getPokemonList should return PokemonListResponse with fail`() {
        // Given
        val error = Throwable()
        every { pokeDexService.getPokemonList(20, 0) } answers {
            Single.error(error)
        }
        // When
        val result = pokeDexRemoteDataSource.getPokemonList(20, 0).test()

        // Then
        result
            .assertNotComplete()
            .assertError(error)
    }
}