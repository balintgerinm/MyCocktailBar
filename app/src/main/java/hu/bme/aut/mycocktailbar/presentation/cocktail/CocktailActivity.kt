package hu.bme.aut.mycocktailbar.presentation.cocktail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.DocumentChange
import hu.bme.aut.mycocktailbar.R
import hu.bme.aut.mycocktailbar.databinding.ActivityCocktailBinding
import hu.bme.aut.mycocktailbar.model.CocktailResult
import hu.bme.aut.mycocktailbar.data_access.CocktailInteractor
import hu.bme.aut.mycocktailbar.adapter.CocktailPagerAdapter
import hu.bme.aut.mycocktailbar.data_layer.db.DataBase
import hu.bme.aut.mycocktailbar.model.CocktailModel

class CocktailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCocktailBinding
    private lateinit var cocktailId: String
    private lateinit var cocktail: CocktailResult

    private lateinit var cocktailPagerAdapter: CocktailPagerAdapter
    private lateinit var interactor: CocktailInteractor
    private lateinit var savedCocktail: CocktailResult
    companion object {
        private const val TAG = "CocktailActivity"
        const val EXTRA_COCKTAIL_ID = "extra.cocktail_id"
        const val EXTRA_COCKTAIL_NAME = "extra.cocktail_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCocktailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cocktailId = intent.getStringExtra(EXTRA_COCKTAIL_ID).toString()
        supportActionBar?.title = intent.getStringExtra(EXTRA_COCKTAIL_NAME)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        interactor = CocktailInteractor()
        val bundle = intent.getBundleExtra("Bundle")
        val saved = bundle?.getParcelable<CocktailModel>("key")
        if (saved != null)
            savedCocktail = CocktailResult(listOf(saved))
    }

    override fun onResume() {
        super.onResume()
        if (savedCocktail.drinks.isEmpty()) {
            loadCocktailData()
        }
        else {
            initFragments(savedCocktail)
        }

    }

    private fun loadCocktailData() {
        val cocktailInteractor = CocktailInteractor()
        cocktailInteractor.getById(cocktailId , onSuccess = this::initFragments, onError = this::showError)
    }

    private fun initFragments(data: CocktailResult) {
        cocktail = data
        cocktailPagerAdapter = CocktailPagerAdapter(cocktail.drinks[0],this)
        binding.mainViewPager.adapter = cocktailPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.mainViewPager) { tab, position ->
            tab.text = when(position) {
                0 -> getString(R.string.main)
                1 -> getString(R.string.details)
                else -> ""
            }
        }.attach()
    }

    private fun showError(e: Throwable) {
        e.printStackTrace()
    }
}
