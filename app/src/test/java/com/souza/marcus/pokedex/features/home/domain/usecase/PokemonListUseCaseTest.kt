package com.souza.marcus.pokedex.features.home.domain.usecase

import com.souza.marcus.pokedex.features.home.domain.model.Pokemon
import com.souza.marcus.pokedex.features.home.domain.repository.PokeDexRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test


internal class PokemonListUseCaseTest {

    @MockK
    lateinit var pokeDexRepository: PokeDexRepository

    private lateinit var pokemonListUseCase: PokemonListUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        pokemonListUseCase = PokemonListUseCase(pokeDexRepository)
    }

    @Test
    fun `should return a list of pokemon with success`() {
        // Given
        val pokemonList = mockk<List<Pokemon>>()
        every { pokeDexRepository.getPokemonList(20, 0) } answers { Single.just(pokemonList) }

        // When
        val result = pokemonListUseCase.invoke(20, 0).test()

        // Then
        result
            .assertNoErrors()
            .assertComplete()
    }
}