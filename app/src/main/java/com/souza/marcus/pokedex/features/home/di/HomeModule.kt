package com.souza.marcus.pokedex.features.home.di

import com.souza.marcus.pokedex.features.home.data.remote.api.PokeDexService
import com.souza.marcus.pokedex.features.home.data.remote.datasource.PokeDexRemoteDataSource
import com.souza.marcus.pokedex.features.home.data.remote.datasource.impl.PokeDexRemoteDataSourceImpl
import com.souza.marcus.pokedex.features.home.data.repository.PokeDexRepositoryImpl
import com.souza.marcus.pokedex.features.home.domain.mapper.PokemonMapper
import com.souza.marcus.pokedex.features.home.domain.mapper.impl.PokemonMapperImpl
import com.souza.marcus.pokedex.features.home.domain.repository.PokeDexRepository
import com.souza.marcus.pokedex.features.home.domain.usecase.PokemonListUseCase
import com.souza.marcus.pokedex.features.home.presentation.viewmodel.HomeViewModel
import com.souza.marcus.pokedex.remotedata.provider.HttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object HomeModule {
    private val dataModule = module {
        factory<PokeDexRemoteDataSource> {
            PokeDexRemoteDataSourceImpl(
                pokeDexService = get<HttpClient>().create(PokeDexService::class.java)
            )
        }
        factory<PokeDexRepository> {
            PokeDexRepositoryImpl(
                remoteDataSource = get(),
                pokemonMapper = get()
            )
        }
    }
    private val domainModule = module {
        factory<PokemonMapper> { PokemonMapperImpl() }
        factory {
            PokemonListUseCase(
                repository = get()
            )
        }
    }

    private val presentationModule = module {
        viewModel {
            HomeViewModel(
                pokemonListUseCase = get()
            )
        }
    }

    fun load() {
        loadKoinModules(
            listOf(
                dataModule,
                domainModule,
                presentationModule
            )
        )
    }
}