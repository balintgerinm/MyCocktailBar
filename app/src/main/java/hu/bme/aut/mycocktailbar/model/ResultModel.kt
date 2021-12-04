package hu.bme.aut.mycocktailbar.model

data class ResultModel(
    val cocktailId: Int,
    val imgUrl: String? = null,
    val name: String? = null,
)