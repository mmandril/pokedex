package com.souza.marcus.pokedex.features.home.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.souza.marcus.pokedex.R
import com.souza.marcus.pokedex.databinding.FragmentHomeBinding
import com.souza.marcus.pokedex.features.home.domain.model.Pokemon
import com.souza.marcus.pokedex.features.home.presentation.adapter.PokemonListAdapter
import com.souza.marcus.pokedex.features.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val pokemonListAdapter: PokemonListAdapter by lazy { PokemonListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        init()
    }

    private fun init() {
        setupObservers()
        setupRecyclerView()
        setupEmptyView()
        viewModel.getPokemonList()
    }

    private fun setupObservers() {
        setupViewStateObservers()
    }

    private fun setupViewStateObservers() {
        with(viewModel) {
            homeViewStateLiveData.observe(viewLifecycleOwner, {
                isLoading(it.isLoading)
                isEmpty(it.isEmpty)
                setPokemonList(it.pokemonList)
            })
        }
    }

    private fun setPokemonList(pokemonList: List<Pokemon>) {
        pokemonListAdapter.addToPokemonList(pokemonList)
        pokemonListAdapter.notifyDataSetChanged()
    }

    private fun isLoading(isLoading: Boolean) {
        binding.homeProgressBar.isVisible = isLoading
    }

    private fun isEmpty(isEmpty: Boolean) {

    }

    private fun setupEmptyView() {

    }

    private fun setupRecyclerView() {
        with(binding.homeRecyclerView) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = pokemonListAdapter
        }
    }
}