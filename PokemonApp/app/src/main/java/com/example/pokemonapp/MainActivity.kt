package com.example.pokemonapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.model.AllPokemonResponse
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.pokemonListView.layoutManager = LinearLayoutManager(this)
        var pokemonRepo = PokemonRepository()
        val pokemonLiveData: LiveData<AllPokemonResponse?> = pokemonRepo.getAll()
        pokemonLiveData.observe(
            this, Observer {
                binding.pokemonListView.adapter = RecyclerViewAdapter(it, fun(p: Pokemon) {
                    val intent: Intent = Intent(this, PokemonFactActivity::class.java)
                    intent.putExtra("name", p.name)
                    startActivity(intent)
                })
            })
    }
}
