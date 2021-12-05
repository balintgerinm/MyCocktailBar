package hu.bme.aut.mycocktailbar.presentation.fragments

import android.os.Bundle
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
        Picasso.get().load(cocktail.imgUrl).into(binding.ivFragmentMain)
        binding.tvIngredients.text = "YAAY"

    }
}
