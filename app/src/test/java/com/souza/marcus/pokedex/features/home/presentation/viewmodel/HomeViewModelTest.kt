package com.souza.marcus.pokedex.features.home.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.souza.marcus.pokedex.features.home.domain.model.Pokemon
import com.souza.marcus.pokedex.features.home.domain.usecase.PokemonListUseCase
import com.souza.marcus.pokedex.features.home.presentation.viewstate.HomeViewState
import com.souza.marcus.pokedex.rules.RxSchedulerRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class HomeViewModelTest {

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    lateinit var pokemonListUseCase: PokemonListUseCase

    @MockK
    lateinit var observerHomeViewState: Observer<HomeViewState>

    private lateinit var homeViewModel: HomeViewModel

    private var homeViewState = HomeViewState()

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        homeViewModel = HomeViewModel(pokemonListUseCase)
        homeViewModel.homeViewStateLiveData.observeForever(observerHomeViewState)
    }

    @Test
    fun `getPokemonList should get PokemonList success and emmit the correct view state order`() {
        // Given
        val pokemonList = mockk<List<Pokemon>>(relaxed = true)
        val loadingViewState = homeViewState.copy(
            isLoading = true
        )
        val finalViewState = homeViewState.copy(
            isLoading = false,
            isError = false,
            isEmpty = pokemonList.isEmpty(),
            pokemonList = pokemonList
        )
        every { pokemonListUseCase.invoke(20, 0) } answers { Single.just(pokemonList) }

        // When
        homeViewModel.getPokemonList(20, 0)

        // Then
        verifyOrder {
            observerHomeViewState.onChanged(loadingViewState)
            observerHomeViewState.onChanged(finalViewState)
        }
    }

    @Test
    fun `getPokemonList should get PokemonList error and emmit the correct view state order`() {
        // Given
        val error = Throwable()
        val loadingViewState = homeViewState.copy(
            isLoading = true
        )
        val finalViewState = homeViewState.copy(
            isLoading = false,
            isError = true,
            isEmpty = true,
            pokemonList = emptyList()
        )
        every { pokemonListUseCase.invoke(20, 0) } answers { Single.error(error) }

        // When
        homeViewModel.getPokemonList(20, 0)

        // Then
        verifyOrder {
            observerHomeViewState.onChanged(loadingViewState)
            observerHomeViewState.onChanged(finalViewState)
        }
    }

    @After
    fun after() {
        homeViewModel.homeViewStateLiveData.removeObserver(observerHomeViewState)
    }
}