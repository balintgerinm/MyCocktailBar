package hu.bme.aut.mycocktailbar.model

import com.squareup.moshi.Json

data class CocktailModel(
    @Json(name = "idDrink") val cocktailId: Int,
    @Json(name = "strDrink") val name: String,
    @Json(name = "strCategory") val category: String,
    @Json(name = "strDrinkThumb") val imgUrl: String,
    @Json(name = "strInstructions") val instruction: String,

    @Json(name = "strIngredient1") val ing1: String?,
    @Json(name = "strIngredient2") val ing2: String?,
    @Json(name = "strIngredient3") val ing3: String?,
    @Json(name = "strIngredient4") val ing4: String?,
    @Json(name = "strIngredient5") val ing5: String?,
    @Json(name = "strIngredient6") val ing6: String?,
    @Json(name = "strIngredient7") val ing7: String?,
    @Json(name = "strIngredient8") val ing8: String?,
    @Json(name = "strIngredient9") val ing9: String?,
    @Json(name = "strIngredient10") val ing10: String?,
    @Json(name = "strIngredient11") val ing11: String?,
    @Json(name = "strIngredient12") val ing12: String?,
    @Json(name = "strIngredient13") val ing13: String?,
    @Json(name = "strIngredient14") val ing14: String?,
    @Json(name = "strIngredient15") val ing15: String?,

    @Json(name = "strMeasure1") val meas1: String?,
    @Json(name = "strMeasure2") val meas2: String?,
    @Json(name = "strMeasure3") val meas3: String?,
    @Json(name = "strMeasure4") val meas4: String?,
    @Json(name = "strMeasure5") val meas5: String?,
    @Json(name = "strMeasure6") val meas6: String?,
    @Json(name = "strMeasure7") val meas7: String?,
    @Json(name = "strMeasure8") val meas8: String?,
    @Json(name = "strMeasure9") val meas9: String?,
    @Json(name = "strMeasure10") val meas10: String?,
    @Json(name = "strMeasure11") val meas11: String?,
    @Json(name = "strMeasure12") val meas12: String?,
    @Json(name = "strMeasure13") val meas13: String?,
    @Json(name = "strMeasure14") val meas14: String?,
    @Json(name = "strMeasure15") val meas15: String?,
)