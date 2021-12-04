package hu.bme.aut.mycocktailbar.model

import com.squareup.moshi.Json

data class ApiResult(
    @Json(name = "drinks")
    val drinks: List<ResultModel>
)