package com.souza.marcus.pokedex.features.home.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.souza.marcus.pokedex.R
import com.souza.marcus.pokedex.databinding.FragmentHomeBinding
import com.souza.marcus.pokedex.features.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        init()
    }

    private fun init() {
        setupObservers()
        viewModel.getPokemonList()
    }

    private fun setupObservers() {
        setupViewStateObservers()
    }

    private fun setupViewStateObservers() {
        with(viewModel) {
            homeViewStateLiveData.observe(viewLifecycleOwner, {
                isLoading(it.isLoading)
            })
        }
    }

    private fun isLoading(isLoading: Boolean) {
        binding.homeProgressBar.isVisible = isLoading
    }
}