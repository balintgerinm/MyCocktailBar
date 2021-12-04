package hu.bme.aut.mycocktailbar.network

import hu.bme.aut.mycocktailbar.model.ApiResult
import hu.bme.aut.mycocktailbar.model.CocktailResult
import hu.bme.aut.mycocktailbar.model.ResultModel
import retrofit2.Call
import retrofit2.http.*

interface CocktailApi {

    companion object {
        const val ENDPOINT_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    }

    @GET("filter.php?")
    fun getByIngredient(
        @Query("i") ingredient: String
        ): Call<ApiResult>

    @GET("lookup.php?")
    fun getById(
        @Query("i") id: String
    ): Call<CocktailResult>
}