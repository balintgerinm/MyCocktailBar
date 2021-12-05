package hu.bme.aut.mycocktailbar.presentation.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.mycocktailbar.databinding.ActivityNetworklistBinding
import hu.bme.aut.mycocktailbar.model.ApiResult
import hu.bme.aut.mycocktailbar.model.ResultModel
import hu.bme.aut.mycocktailbar.data_access.CocktailInteractor
import hu.bme.aut.mycocktailbar.adapter.CocktailAdapter
import hu.bme.aut.mycocktailbar.presentation.cocktail.CocktailActivity

class NetworkListActivity: AppCompatActivity(),
    CocktailAdapter.OnCocktailSelectedListener {

    private lateinit var binding: ActivityNetworklistBinding
    private var adapter: CocktailAdapter? = null


    private lateinit var ingredient: String

    companion object {
        private const val TAG = "NetworkListActivity"
        const val EXTRA_INGREDIENT = "extra.ingredient"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworklistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ingredient = intent.getStringExtra(EXTRA_INGREDIENT).toString()
        adapter = CocktailAdapter(this, 1)
        binding.rwCocktailNetwork.adapter = adapter
        binding.rwCocktailNetwork.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        loadDataI()
    }


    private fun loadDataI() {
        val cocktailInteractor = CocktailInteractor()
        cocktailInteractor.getByIngredient(ingredient , onSuccess = this::showList, onError = this::showError)
    }

    private fun showList(data: ApiResult) {
        for(item in data.drinks)
            adapter?.addCocktail(item)
    }

    private fun showError(e: Throwable) {
        e.printStackTrace()
    }

    override fun onCocktailSelected(cocktail: ResultModel?) {
        val showCocktailIntent = Intent()
        showCocktailIntent.setClass(this@NetworkListActivity, CocktailActivity::class.java)
        showCocktailIntent.putExtra(CocktailActivity.EXTRA_COCKTAIL_ID, cocktail?.cocktailId.toString())
        showCocktailIntent.putExtra(CocktailActivity.EXTRA_COCKTAIL_NAME, cocktail?.name)
        startActivity(showCocktailIntent)
    }
}