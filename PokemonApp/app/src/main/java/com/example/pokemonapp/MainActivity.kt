package com.example.pokemonapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.pokemonListView.layoutManager = LinearLayoutManager(this)
        var pokemonRepo = PokemonRepository()
        // Get all data as live data
        var pokemonLiveData: LiveData<AllPokemonResponse?> = pokemonRepo.getAll()
        // Create recycler view adapter from that live data
        // Observer the live data and assign it recycler view adapter

        pokemonLiveData.observe(
            this, Observer {
                recyclerViewAdapter = RecyclerViewAdapter(it, fun(p: Pokemon) {
                    val intent: Intent = Intent(this, PokemonFactActivity::class.java)
                    intent.putExtra("name", p.name)
                    startActivity(intent)
                })
                binding.pokemonListView.adapter = recyclerViewAdapter
            })
        // Add on scroll if all items are being read then fetch new items
        binding.pokemonListView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // If recyclerView is not able to scroll more vertically
                if (!recyclerView.canScrollVertically(1)) {
                    val offsetRegex = Regex("offset=(\\d+)")
                    // get the next url from the pokemonLiveData
                    val matchResult = offsetRegex.find(pokemonLiveData.value?.next.toString())
                    val offsetValue : Int? = matchResult?.groupValues?.get(1)?.toInt()
                    if (offsetValue != null) {
                        pokemonLiveData = pokemonRepo.getAll(offsetValue)
                        // as the live data is being returned observer when the data will be available , once it is then perform task
                        pokemonLiveData.observe(this@MainActivity, Observer{
                            // when the pokemon live data is going to be udpated then
                            // Update the list of the items in the recycler view
                            recyclerViewAdapter?.updateList(it)
                            // Tell recycler view that the list of the items has been update
                            recyclerViewAdapter?.notifyDataSetChanged()
                        })
                    }
                }
            }
        })
    }
}
