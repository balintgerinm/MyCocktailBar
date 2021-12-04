package hu.bme.aut.mycocktailbar.model

data class CocktailModel(
    val cocktailId: Int,
    val name: String?,
    val category: String?,
    val imgUrl: String?,
    val instruction: String?,
    val ingredients: List<String?>,
    val measures: List<String?>
)