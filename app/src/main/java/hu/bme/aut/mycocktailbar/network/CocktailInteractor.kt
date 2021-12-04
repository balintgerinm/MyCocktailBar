package hu.bme.aut.mycocktailbar.network

import android.os.Handler
import android.os.Looper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.bme.aut.mycocktailbar.model.ApiResult
import hu.bme.aut.mycocktailbar.model.ResultModel
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.concurrent.thread
import kotlin.reflect.KFunction0

class CocktailInteractor {
    private val cocktailApi: CocktailApi

    init {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(CocktailApi.ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        this.cocktailApi = retrofit.create(CocktailApi::class.java)
    }

    private fun <T> runCallOnBackgroundThread(
        call: Call<T>,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val handler = Handler(Looper.getMainLooper()!!)
        thread {
            try {
                val response = call.execute().body()!!
                handler.post { onSuccess(response) }

            } catch (e: Exception) {
                e.printStackTrace()
                handler.post { onError(e) }
            }
        }
    }

    fun getByIngredient(
        ingredient: String,
        onSuccess: (ApiResult) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val getByIngredientsRequest = cocktailApi.getByIngredient(ingredient)
        runCallOnBackgroundThread(getByIngredientsRequest, onSuccess, onError)
    }
}