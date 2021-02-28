package com.souza.marcus.pokedex.features.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.souza.marcus.pokedex.features.home.domain.model.Pokemon
import com.souza.marcus.pokedex.features.home.domain.usecase.PokemonListUseCase
import com.souza.marcus.pokedex.features.home.presentation.viewstate.HomeViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

internal class HomeViewModel(
    private val pokemonListUseCase: PokemonListUseCase
) : ViewModel() {

    private val disposable by lazy { CompositeDisposable() }
    private val _homeViewStateLiveData by lazy { MutableLiveData<HomeViewState>() }
    val homeViewStateLiveData: LiveData<HomeViewState> = _homeViewStateLiveData
    private val homeViewState = HomeViewState()

    fun getPokemonList(limit: Int = 20, offset: Int = 0) {
        pokemonListUseCase(limit, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .subscribe(::handleSuccess, ::handleError)
            .apply { disposable.add(this) }
    }

    private fun showLoading() {
        _homeViewStateLiveData.value = homeViewState.copy(
            isLoading = true
        )
    }

    private fun handleSuccess(pokemonList: List<Pokemon>) {
        _homeViewStateLiveData.value = homeViewState.copy(
            isLoading = false,
            isError = false,
            isEmpty = pokemonList.isEmpty(),
            pokemonList = pokemonList
        )
    }

    private fun handleError(error: Throwable) {
        _homeViewStateLiveData.value = homeViewState.copy(
            isLoading = false,
            isError = true,
            isEmpty = true,
            pokemonList = emptyList()
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable.isDisposed.not()) disposable.clear()
    }
}