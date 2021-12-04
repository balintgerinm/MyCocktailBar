package hu.bme.aut.mycocktailbar.presentation.list

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import hu.bme.aut.mycocktailbar.R
import hu.bme.aut.mycocktailbar.databinding.DialogSearchCocktailBinding

class SearchCocktailDialogFragment : AppCompatDialogFragment() {

    private lateinit var binding: DialogSearchCocktailBinding
    private lateinit var listener: SearchCocktailDialogListener

    interface SearchCocktailDialogListener {
        fun onCocktailSearched(ingredient: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding = DialogSearchCocktailBinding.inflate(LayoutInflater.from(context))

        try {
            listener = if (targetFragment != null) {
                targetFragment as SearchCocktailDialogListener
            } else {
                activity as SearchCocktailDialogListener
            }
        } catch (e: ClassCastException) {
            throw RuntimeException(e)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.search_cocktail)
            .setView(binding.root)
            .setPositiveButton(R.string.ok) { _, _ ->
                listener.onCocktailSearched(
                    binding.SearchCocktailDialogEditText.text.toString()
                )
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }
}
