package hu.bme.aut.mycocktailbar.network

import hu.bme.aut.mycocktailbar.model.ApiResult
import hu.bme.aut.mycocktailbar.model.ResultModel
import retrofit2.Call
import retrofit2.http.*

interface CocktailApi {

    companion object {
        const val ENDPOINT_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    }

    @GET("filter.php?")
    fun getByIngredient(
        @Query("i") ingredient: String,

        ): Call<ApiResult>
}

/*
@GET()
    fun getByIngredient(
        @Url url: String,
        @Header("x-rapidapi-host") host: String,
        @Header("x-rapidapi-key") key: String
    ): Call<ApiResult?>?
 */