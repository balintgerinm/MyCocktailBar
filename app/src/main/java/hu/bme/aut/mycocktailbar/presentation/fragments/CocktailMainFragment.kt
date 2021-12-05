package hu.bme.aut.mycocktailbar.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.util.StringBuilderPrinter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import hu.bme.aut.mycocktailbar.databinding.FragmentCocktailMainBinding
import hu.bme.aut.mycocktailbar.model.CocktailModel
import java.lang.StringBuilder

class CocktailMainFragment(private var cocktail: CocktailModel) : Fragment() {

    private lateinit var binding: FragmentCocktailMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = FragmentCocktailMainBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayCocktailData()
    }

    private fun displayCocktailData() {
        binding.tvFragmentMain.text = cocktail.category
        binding.tvIngredients.text = buildIngredientString()
        Picasso.get().load(cocktail.imgUrl).into(binding.ivFragmentMain)


    }

    private fun buildIngredientString(): String {
        var ingredients = listOf(
            cocktail.ing1, cocktail.ing2, cocktail.ing3, cocktail.ing4, cocktail.ing5,
            cocktail.ing6, cocktail.ing7, cocktail.ing8, cocktail.ing9, cocktail.ing10,
            cocktail.ing11, cocktail.ing12, cocktail.ing13, cocktail.ing14, cocktail.ing15
        )
        var measures = listOf(
            cocktail.meas1, cocktail.meas2, cocktail.meas3, cocktail.meas4, cocktail.meas5,
            cocktail.meas6, cocktail.meas7, cocktail.meas8, cocktail.meas9, cocktail.meas10,
            cocktail.meas11, cocktail.meas12, cocktail.meas13, cocktail.meas14, cocktail.meas15,
        )
        var out = ""
        for (i in 0..14) {
            ingredients[i]?.let { it1 ->
                out += it1
                out += (" ....... ")
                measures[i]?.let { it2 ->
                    out += it2
                }
                out += "\n"
            }
        }
        return out.toString()
    }
}
