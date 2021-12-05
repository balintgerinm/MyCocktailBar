package hu.bme.aut.mycocktailbar.presentation.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.toObject
import hu.bme.aut.mycocktailbar.databinding.ActivityCocktaillistBinding
import hu.bme.aut.mycocktailbar.model.ResultModel
import hu.bme.aut.mycocktailbar.presentation.fragments.SearchCocktailDialogFragment
import hu.bme.aut.mycocktailbar.adapter.CocktailAdapter
import hu.bme.aut.mycocktailbar.data_access.CocktailInteractor
import hu.bme.aut.mycocktailbar.model.CocktailModel
import hu.bme.aut.mycocktailbar.presentation.cocktail.CocktailActivity

class CocktailListActivity : AppCompatActivity(),
    CocktailAdapter.OnCocktailSelectedListener,
    SearchCocktailDialogFragment.SearchCocktailDialogListener {

    private lateinit var binding: ActivityCocktaillistBinding
    private lateinit var adapter: CocktailAdapter
    private lateinit var interactor: CocktailInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCocktaillistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        interactor = CocktailInteractor()
        initFab()
        initRecyclerView()
        initDbListener()
    }

    private fun initFab() {
        binding.fab.setOnClickListener {
            SearchCocktailDialogFragment().show(supportFragmentManager, SearchCocktailDialogFragment::class.java.simpleName)
        }
    }

    private fun initRecyclerView() {
        binding.rwCocktail.layoutManager = LinearLayoutManager(this)
        adapter = CocktailAdapter(this, 0)

        /*adapter.addCocktail(ResultModel("mojito", "https://www.thecocktaildb.com/images/media/drink/rrtssw1472668972.jpg", 10017))
        adapter.addCocktail(ResultModel("gin tonic", "https://www.thecocktaildb.com/images/media/drink/vqyxqx1472669095.jpg", 10048))
        adapter.addCocktail(ResultModel("margerita", "https://www.thecocktaildb.com/images/media/drink/2x8thr1504816928.jpg", 10097))*/

        binding.rwCocktail.adapter = adapter
    }

    override fun onCocktailSelected(cocktail: ResultModel?) {
        val showCocktailIntent = Intent()
        showCocktailIntent.setClass(this@CocktailListActivity, CocktailActivity::class.java)
        showCocktailIntent.putExtra(CocktailActivity.EXTRA_COCKTAIL_ID, cocktail?.cocktailId.toString())
        showCocktailIntent.putExtra(CocktailActivity.EXTRA_COCKTAIL_NAME, cocktail?.name)

        val selected = cocktail?.let { adapter.getFavorite(it.cocktailId) }
        if (selected != null) {
            val bundle = Bundle()
            val parcel = CocktailModel(
                selected.cocktailId, selected.name, selected.category,
                selected.imgUrl, selected.instruction,
                selected.ing1, selected.ing2, selected.ing3, selected.ing4,
                selected.ing5, selected.ing6, selected.ing7, selected.ing8,
                selected.ing9, selected.ing10, selected.ing11, selected.ing12,
                selected.ing13, selected.ing14, selected.ing15,
                selected.meas1, selected.meas2, selected.meas3, selected.meas4,
                selected.meas5, selected.meas6, selected.meas7, selected.meas8,
                selected.meas9, selected.meas10, selected.meas11, selected.meas12,
                selected.meas13, selected.meas14, selected.meas15
            )
            bundle.putParcelable("key", parcel)
            showCocktailIntent.putExtra("Bundle", bundle)
        }
        startActivity(showCocktailIntent)
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

    private fun initDbListener() {
        interactor.getDbInstance().addCocktailsListener()
        { snapshots, error ->
            if (error != null) {
                return@addCocktailsListener
            }
            for (change in snapshots!!.documentChanges) {
                val res = change.document.toObject<CocktailModel>()
                when (change.type) {
                    DocumentChange.Type.ADDED -> {
                        res.name?.let { res.imgUrl?.let { it1 ->
                            res.cocktailId?.let { it2 ->
                                ResultModel(it,
                                    it1, it2
                                )
                            }
                        } }?.let {
                            adapter.addCocktail(
                                it
                            )
                        }
                        adapter.addFavorite(res)
                    }
                    DocumentChange.Type.REMOVED -> {
                        if (res.cocktailId != null)
                            adapter.removeCocktail(res.cocktailId)
                    }
                }
            }
        }
    }
}
