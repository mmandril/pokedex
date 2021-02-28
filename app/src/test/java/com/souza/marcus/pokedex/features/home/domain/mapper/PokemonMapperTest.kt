package com.souza.marcus.pokedex.features.home.domain.mapper

import com.souza.marcus.pokedex.features.home.data.remote.model.PokemonListResponse
import com.souza.marcus.pokedex.features.home.data.remote.model.PokemonResponse
import com.souza.marcus.pokedex.features.home.domain.mapper.impl.PokemonMapperImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotNull

internal class PokemonMapperTest {

    private val pokemonMapper: PokemonMapper = PokemonMapperImpl()

    @Test
    fun `should return list of Pokemon from mapper`() {
        // Given
        val pokemonListResponse = mockk<PokemonListResponse>()

        // When
        every { pokemonListResponse.results } answers { ArrayList<PokemonResponse>() }
        val result = pokemonMapper.transform(pokemonListResponse)

        //Then
        assertNotNull(result)
    }
}