package hu.bme.aut.mycocktailbar.presentation.list

import android.content.Intent
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

    private lateinit var data: MutableList<ResultModel>

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

        adapter.addCocktail(ResultModel("mojito", "https://www.thecocktaildb.com/images/media/drink/rrtssw1472668972.jpg", 1))
        adapter.addCocktail(ResultModel("gin tonic", "https://www.thecocktaildb.com/images/media/drink/vqyxqx1472669095.jpg", 148))
        adapter.addCocktail(ResultModel("margerita", "https://www.thecocktaildb.com/images/media/drink/2x8thr1504816928.jpg", 197))

        binding.rwCocktail.adapter = adapter
    }

    override fun onCocktailSelected(cocktail: ResultModel?) {
        // Todo: Start DetailsActivity with the selected cocktail
    }

    fun onCocktailAdded(cocktail: ResultModel?) {
        adapter.addCocktail(cocktail!!)
    }

    override fun onCocktailSearched(ingredient: String?) {
        val searchIntent = Intent()
        searchIntent.setClass(this@CocktailListActivity ,NetworkListActivity::class.java)
        searchIntent.putExtra(NetworkListActivity.EXTRA_INGREDIENT, ingredient)
        startActivity(searchIntent)
    }
}
