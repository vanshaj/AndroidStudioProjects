package com.example.pokemonapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.pokemonapp.model.AllPokemonResponse
import com.example.pokemonapp.model.PokemonRepository

class PokemonViewModel(private val pokeRepo: PokemonRepository) : ViewModel() {
    fun getAll(offset: Int, limit: Int): LiveData<AllPokemonResponse?> {
        return liveData {
            val response = pokeRepo.getAll(offset, limit)
            emit(response)
        }
    }
}