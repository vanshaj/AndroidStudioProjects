package com.example.pokemonapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonapp.model.PokemonRepository

class PokemonViewModelFactory(val pokeRepo: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modeClass: Class<T>): T {
        return PokemonViewModel(pokeRepo) as T
    }
}