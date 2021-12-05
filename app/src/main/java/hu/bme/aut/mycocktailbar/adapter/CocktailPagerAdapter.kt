package hu.bme.aut.mycocktailbar.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import hu.bme.aut.mycocktailbar.model.CocktailModel
import hu.bme.aut.mycocktailbar.model.CocktailResult
import hu.bme.aut.mycocktailbar.presentation.fragments.CocktailInstructionFragment
import hu.bme.aut.mycocktailbar.presentation.fragments.CocktailMainFragment

class CocktailPagerAdapter(private var cocktail: CocktailModel, fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    companion object {
        private const val NUM_PAGES: Int = 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CocktailMainFragment(cocktail)
            1 -> CocktailInstructionFragment(cocktail)
            else -> CocktailMainFragment(cocktail)
        }
    }

    override fun getItemCount(): Int = NUM_PAGES
}
















