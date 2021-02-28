package com.souza.marcus.pokedex.features.home.data.repository

import com.souza.marcus.pokedex.features.home.data.remote.datasource.PokeDexRemoteDataSource
import com.souza.marcus.pokedex.features.home.data.remote.model.PokemonListResponse
import com.souza.marcus.pokedex.features.home.domain.mapper.PokemonMapper
import com.souza.marcus.pokedex.features.home.domain.model.Pokemon
import com.souza.marcus.pokedex.features.home.domain.repository.PokeDexRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

internal class PokeDexRepositoryTest {

    @MockK
    lateinit var remoteDataSource: PokeDexRemoteDataSource

    @MockK
    lateinit var pokemonMapper: PokemonMapper

    private lateinit var pokeDexRepository: PokeDexRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        pokeDexRepository = PokeDexRepositoryImpl(remoteDataSource, pokemonMapper)
    }

    @Test
    fun `getPokemonList should return a list of Pokemon with success`() {
        // Given
        val mockedPokemonListResponse = mockk<PokemonListResponse>()
        val mockedPokemonList = mockk<List<Pokemon>>()
        every { remoteDataSource.getPokemonList(20, 0) } answers {
            Single.just(
                mockedPokemonListResponse
            )
        }
        every { pokemonMapper.transform(mockedPokemonListResponse) } answers { mockedPokemonList }

        // When
        val result = pokeDexRepository.getPokemonList(20, 0).test()

        // Then
        result
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun `getPokemonList should return error when data source fail`() {
        // Given
        val error = Throwable()
        every { remoteDataSource.getPokemonList(20, 0) } answers { Single.error(error) }

        // When
        val result = pokeDexRepository.getPokemonList(20, 0).test()

        // Then
        result
            .assertNotComplete()
            .assertError(error)
    }

    @Test
    fun `getPokemonList should return error when mapper fail`() {
        // Given
        val mockedPokemonListResponse = mockk<PokemonListResponse>()
        val runtimeException = RuntimeException()
        every { remoteDataSource.getPokemonList(20, 0) } answers {
            Single.just(
                mockedPokemonListResponse
            )
        }
        every { pokemonMapper.transform(mockedPokemonListResponse) } throws (runtimeException)

        // When
        val result = pokeDexRepository.getPokemonList(20, 0).test()

        // Then
        result
            .assertNotComplete()
            .assertError(runtimeException)
    }
}