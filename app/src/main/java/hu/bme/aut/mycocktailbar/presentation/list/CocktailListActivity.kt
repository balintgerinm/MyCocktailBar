package hu.bme.aut.mycocktailbar.presentation.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.mycocktailbar.databinding.ActivityCocktaillistBinding

class CocktailListActivity : AppCompatActivity(), CocktailAdapter.OnCocktailSelectedListener {

    private lateinit var binding: ActivityCocktaillistBinding
    private lateinit var adapter: CocktailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCocktaillistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFab()
        initRecyclerView()
    }

    private fun initFab() {
        binding.fab.setOnClickListener {
            // TODO: Show new city dialog
        }
    }

    private fun initRecyclerView() {
        binding.rwCocktail.layoutManager = LinearLayoutManager(this)
        adapter = CocktailAdapter(this)

        binding.rwCocktail.adapter = adapter
    }

    override fun onCocktailSelected(city: String?) {
        // Todo: Start DetailsActivity with the selected city
    }

    fun onCocktailAdded(cocktail: String?) {
        adapter.addCocktail(cocktail!!)
    }
}
