package com.example.pokemonapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.model.PokemonRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.pokemonListView.layoutManager = LinearLayoutManager(this)
        var pokemonRepo = PokemonRepository()
        var listOfPokemon = pokemonRepo.getAll()
        var pokemonAdapter = RecyclerViewAdapter(listOfPokemon)
        binding.pokemonListView.adapter = pokemonAdapter
        pokemonAdapter.onItemClick = {
            var intent: Intent = Intent(this@MainActivity, PokemonFactActivity::class.java)
            intent.putExtra("pokemon", it)
            startActivity(intent)
        }
    }

}