package hu.bme.aut.mycocktailbar.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.mycocktailbar.R
import hu.bme.aut.mycocktailbar.databinding.ItemCocktailBinding

class CocktailAdapter(private val listener: OnCocktailSelectedListener) : RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder>() {
    private val cocktails: MutableList<String> = ArrayList()

    interface OnCocktailSelectedListener {
        fun onCocktailSelected(cocktail: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cocktail, parent, false)
        return CocktailViewHolder(view)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val item = cocktails[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = cocktails.size

    fun addCocktail(newCocktail: String) {
        cocktails.add(newCocktail)
        notifyItemInserted(cocktails.size - 1)
    }

    fun removeCocktail(position: Int) {
        cocktails.removeAt(position)
        notifyItemRemoved(position)
        if (position < cocktails.size) {
            notifyItemRangeChanged(position, cocktails.size - position)
        }
    }

    inner class CocktailViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemCocktailBinding.bind(itemView)
        var item: String? = null

        init {
            binding.root.setOnClickListener { listener.onCocktailSelected(item) }
        }

        fun bind(newCocktail: String?) {
            item = newCocktail
            // TODO bindings
            //binding.CityItemNameTextView.text = item
        }
    }
}
