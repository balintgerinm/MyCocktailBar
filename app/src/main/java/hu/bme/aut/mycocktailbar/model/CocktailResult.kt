package hu.bme.aut.mycocktailbar.model

import com.squareup.moshi.Json

data class CocktailResult(
    @Json(name = "drinks")
    val drinks: List<CocktailModel>
)