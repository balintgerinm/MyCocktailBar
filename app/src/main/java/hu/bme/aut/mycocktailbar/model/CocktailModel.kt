package hu.bme.aut.mycocktailbar.model

data class CocktailModel(
    val cocktailId: Int,
    val name: String? = null,
    val category: String? = null,
    val imgUrl: String? = null,
    val instruction: String? = null,
    val ingredients: List<String?>,
    val measures: List<String?>
)