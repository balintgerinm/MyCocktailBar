package hu.bme.aut.mycocktailbar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hu.bme.aut.mycocktailbar.R
import hu.bme.aut.mycocktailbar.data_access.CocktailInteractor
import hu.bme.aut.mycocktailbar.data_layer.db.DataBase
import hu.bme.aut.mycocktailbar.databinding.ItemCocktailBinding
import hu.bme.aut.mycocktailbar.model.CocktailModel
import hu.bme.aut.mycocktailbar.model.ResultModel

class CocktailAdapter(private val listener: OnCocktailSelectedListener, private val type: Int)
    : RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder>() {

    private val cocktails: MutableList<ResultModel> = mutableListOf()
    private val interactor = CocktailInteractor()
    private val favorites: MutableList<CocktailModel> = mutableListOf()

    interface OnCocktailSelectedListener {
        fun onCocktailSelected(cocktail: ResultModel?)
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

    fun addCocktail(newCocktail: ResultModel) {
        if(!cocktails.contains(newCocktail)) {
            cocktails.add(newCocktail)
            notifyItemInserted(cocktails.size - 1)
        }
    }

    fun addFavorite(cocktail: CocktailModel) {
        favorites.add(cocktail)
    }
    fun getFavorite(cocktailId: Int): CocktailModel? {
        return favorites.find { cocktail -> cocktail.cocktailId!! == cocktailId }
    }

    fun removeCocktail(cocktailId: Int) {
        var position = -1
        var asked = -1
        for (cocktail in cocktails) {
            position += 1
            if (cocktail.cocktailId == cocktailId) {
                asked = position
                break
            }
        }
        if (asked == -1)
            return
        cocktails.removeAt(position)
        notifyItemRemoved(position)
        if (position < cocktails.size) {
            notifyItemRangeChanged(position, cocktails.size - position)
        }
    }

    inner class CocktailViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemCocktailBinding.bind(itemView)
        var item: ResultModel? = null

        init {
            binding.root.setOnClickListener { listener.onCocktailSelected(item) }
            binding.CocktailItemSaveButton.setOnClickListener {
                item?.let { it1 -> interactor.save(it1.cocktailId) }
            }
            binding.CocktailItemRemoveButton.setOnClickListener {
                interactor.getDbInstance().deleteCocktail(item?.cocktailId.toString())
                item?.let { it1 -> removeCocktail(it1.cocktailId) }
            }
        }

        fun bind(newCocktail: ResultModel?) {
            item = newCocktail
            binding.CocktailItemNameTextView.text = newCocktail?.name
            when (type){
                0 -> {
                    binding.CocktailItemSaveButton.isEnabled = false
                }
                1 -> {
                    binding.CocktailItemRemoveButton.isEnabled = false
                }
            }
            Picasso.get().load(item?.imgUrl).into(binding.ivIcon)
        }
    }

}
