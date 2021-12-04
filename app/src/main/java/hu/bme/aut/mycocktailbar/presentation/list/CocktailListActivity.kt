package hu.bme.aut.mycocktailbar.presentation.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.mycocktailbar.databinding.ActivityCocktaillistBinding
import hu.bme.aut.mycocktailbar.model.ResultModel

class CocktailListActivity : AppCompatActivity(),
    CocktailAdapter.OnCocktailSelectedListener,
    SearchCocktailDialogFragment.SearchCocktailDialogListener {

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
            SearchCocktailDialogFragment().show(supportFragmentManager, SearchCocktailDialogFragment::class.java.simpleName)
        }
    }

    private fun initRecyclerView() {
        binding.rwCocktail.layoutManager = LinearLayoutManager(this)
        adapter = CocktailAdapter(this)

        adapter.addCocktail(ResultModel(1, "https://www.thecocktaildb.com/images/media/drink/rrtssw1472668972.jpg", "mojito"))
        adapter.addCocktail(ResultModel(148, "https://www.thecocktaildb.com/images/media/drink/vqyxqx1472669095.jpg", "gin tonic"))
        adapter.addCocktail(ResultModel(197, "https://www.thecocktaildb.com/images/media/drink/2x8thr1504816928.jpg", "margerita"))

        binding.rwCocktail.adapter = adapter
    }

    override fun onCocktailSelected(cocktail: ResultModel?) {
        // Todo: Start DetailsActivity with the selected cocktail
    }

    fun onCocktailAdded(cocktail: ResultModel?) {
        adapter.addCocktail(cocktail!!)
    }

    override fun onCocktailSearched() {

    }
}
