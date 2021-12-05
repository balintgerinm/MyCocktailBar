package hu.bme.aut.mycocktailbar.data_layer.db


import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import hu.bme.aut.mycocktailbar.model.CocktailModel

class DataBase {
    companion object {
        private const val TAG = "DataBase"
        private const val COCKTAILS = "cocktails"

        private var instance = DataBase()
        fun getInstance(): DataBase {
            return instance
        }
    }

    private val db = Firebase.firestore
    private val cocktails: CollectionReference?
        get() {
            return db.collection(COCKTAILS)
        }

    fun createCocktail(cocktailToSave: CocktailModel) {
        cocktails?.document(cocktailToSave.cocktailId.toString())
            ?.set(cocktailToSave.toHashMap())
            ?.addOnSuccessListener {
                Log.d(TAG, "Cocktail added")
            }
            ?.addOnFailureListener {
                error -> Log.w(TAG, "Error adding", error)
            }
    }

    fun addCocktailsListener(listener: EventListener<QuerySnapshot?>) {
        cocktails
            ?.addSnapshotListener(listener)
    }

    fun deleteCocktail(cocktailId: String) {
        cocktails
            ?.document(cocktailId)
            ?.delete()
            ?.addOnSuccessListener {
                Log.d(TAG, "Cocktail deleted")
            }
            ?.addOnFailureListener { error ->
                Log.w(TAG, "Error deleting", error)
            }
    }

    /*fun getCocktail(cocktailId: String, function: (CocktailModel) -> (Unit)) {
        cocktails
            ?.document(cocktailId)
            ?.get()
            ?.addOnSuccessListener {

            }
            ?.addOnFailureListener { error ->
                Log.w(TAG, "Error finding", error)
            }
    }*/
}