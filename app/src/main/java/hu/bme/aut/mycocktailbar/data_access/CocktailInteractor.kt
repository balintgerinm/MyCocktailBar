package hu.bme.aut.mycocktailbar.data_access

import android.os.Handler
import android.os.Looper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.bme.aut.mycocktailbar.data_layer.db.DataBase
import hu.bme.aut.mycocktailbar.model.ApiResult
import hu.bme.aut.mycocktailbar.model.CocktailResult
import hu.bme.aut.mycocktailbar.data_layer.network.CocktailApi
import hu.bme.aut.mycocktailbar.model.CocktailModel
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.concurrent.thread

class CocktailInteractor {
    private val cocktailApi: CocktailApi
    private val db: DataBase



    init {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(CocktailApi.ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        this.cocktailApi = retrofit.create(CocktailApi::class.java)
        this.db = DataBase.getInstance()
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

    fun getById(
        cocktailId: String,
        onSuccess: (CocktailResult) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val getByIdRequest = cocktailApi.getById(cocktailId)
        runCallOnBackgroundThread(getByIdRequest, onSuccess, onError)
    }

    fun save(cocktailId: Int) {
        getById(cocktailId.toString(), onSuccess = this::saveDb, onError = this::error)
    }


    fun getDbInstance(): DataBase {
        return db
    }

    private fun saveDb(result: CocktailResult) {
        db.createCocktail(result.drinks[0])
    }

    private fun error(err: Throwable) {}
}