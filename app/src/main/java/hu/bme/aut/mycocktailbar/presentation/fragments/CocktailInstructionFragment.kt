package hu.bme.aut.mycocktailbar.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.mycocktailbar.databinding.FragmentCocktailInstructionBinding
import hu.bme.aut.mycocktailbar.model.CocktailModel

class CocktailInstructionFragment(private var cocktail: CocktailModel) : Fragment() {

    private lateinit var binding: FragmentCocktailInstructionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCocktailInstructionBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayCocktailData()
    }

    private fun displayCocktailData() {
        binding.tvFragmentInstruction.text = cocktail.instruction
    }
}
