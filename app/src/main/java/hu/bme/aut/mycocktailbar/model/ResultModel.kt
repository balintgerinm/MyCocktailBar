package hu.bme.aut.mycocktailbar.model

import com.squareup.moshi.Json

data class ResultModel(
    @Json(name = "strDrink") val name: String,
    @Json(name = "strDrinkThumb") val imgUrl: String,
    @Json(name = "idDrink") val cocktailId: Int
)