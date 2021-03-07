package com.souza.marcus.pokedex.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.souza.marcus.pokedex.databinding.PokemonListItemBinding
import com.souza.marcus.pokedex.features.home.domain.model.Pokemon

internal class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    private val pokemonList: MutableList<Pokemon> = mutableListOf()
    private lateinit var pokemonListItemBinding: PokemonListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        pokemonListItemBinding = PokemonListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonViewHolder(
            pokemonListItemBinding
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun addToPokemonList(pokemonList: List<Pokemon>) {
        this.pokemonList.addAll(pokemonList)
    }

    inner class PokemonViewHolder(pokemonListItemBinding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(pokemonListItemBinding.root) {

        fun bind(pokemon: Pokemon) {
            with(pokemonListItemBinding) {
                Glide
                    .with(this.root)
                    .load(pokemon.imageUrl)
                    .listener(
                        GlidePalette.with(pokemon.imageUrl)
                            .use(BitmapPalette.Profile.MUTED_DARK)
                            .intoCallBack { palette ->
                                palette?.dominantSwatch?.rgb?.let {
                                    pokemonItemContainer.setBackgroundColor(it)
                                }
                            }.crossfade(true)
                    )
                    .into(pokemonItemImage)
                pokemonItemName.text = pokemon.name
            }

        }
    }
}
