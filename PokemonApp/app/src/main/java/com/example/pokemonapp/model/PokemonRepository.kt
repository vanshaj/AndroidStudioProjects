package com.example.pokemonapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.pokemonapp.api.PokemonApi
import com.example.pokemonapp.api.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class PokemonRepository {

    fun getAll(offset: Int = 0, limit: Int = 20): LiveData<AllPokemonResponse?> {
        var pokemons: AllPokemonResponse?  = null
        val retrofitInstance: PokemonApi = RetrofitInstance.getRetrofitInstance().create(PokemonApi::class.java)
        return liveData {
            val pokemonResponse : AllPokemonResponse? = retrofitInstance.getAllPokemon(offset, limit).body()
            Log.i("mytag", pokemonResponse?.results?.get(0)?.name.toString())
            emit(pokemonResponse)
        }
    }

    fun getPokemon(id: Int): LiveData<Pokemon> {
        return liveData {
            emit(Pokemon("pikachu", "https://abc"))
        }
    }
}
