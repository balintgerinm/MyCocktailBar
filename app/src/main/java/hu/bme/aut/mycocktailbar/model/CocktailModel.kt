package hu.bme.aut.mycocktailbar.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import java.io.Serializable

data class CocktailModel(
    @Json(name = "idDrink") val cocktailId: Int? = null,
    @Json(name = "strDrink") val name: String?= null,
    @Json(name = "strCategory") val category: String? = null,
    @Json(name = "strDrinkThumb") val imgUrl: String? = null,
    @Json(name = "strInstructions") val instruction: String? = null,

    @Json(name = "strIngredient1") val ing1: String? = null,
    @Json(name = "strIngredient2") val ing2: String? = null,
    @Json(name = "strIngredient3") val ing3: String? = null,
    @Json(name = "strIngredient4") val ing4: String? = null,
    @Json(name = "strIngredient5") val ing5: String? = null,
    @Json(name = "strIngredient6") val ing6: String? = null,
    @Json(name = "strIngredient7") val ing7: String? = null,
    @Json(name = "strIngredient8") val ing8: String? = null,
    @Json(name = "strIngredient9") val ing9: String? = null,
    @Json(name = "strIngredient10") val ing10: String? = null,
    @Json(name = "strIngredient11") val ing11: String? = null,
    @Json(name = "strIngredient12") val ing12: String? = null,
    @Json(name = "strIngredient13") val ing13: String? = null,
    @Json(name = "strIngredient14") val ing14: String? = null,
    @Json(name = "strIngredient15") val ing15: String? = null,

    @Json(name = "strMeasure1") val meas1: String? = null,
    @Json(name = "strMeasure2") val meas2: String? = null,
    @Json(name = "strMeasure3") val meas3: String? = null,
    @Json(name = "strMeasure4") val meas4: String? = null,
    @Json(name = "strMeasure5") val meas5: String? = null,
    @Json(name = "strMeasure6") val meas6: String? = null,
    @Json(name = "strMeasure7") val meas7: String? = null,
    @Json(name = "strMeasure8") val meas8: String? = null,
    @Json(name = "strMeasure9") val meas9: String? = null,
    @Json(name = "strMeasure10") val meas10: String? = null,
    @Json(name = "strMeasure11") val meas11: String? = null,
    @Json(name = "strMeasure12") val meas12: String? = null,
    @Json(name = "strMeasure13") val meas13: String? = null,
    @Json(name = "strMeasure14") val meas14: String? = null,
    @Json(name = "strMeasure15") val meas15: String? = null,
) : Serializable, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    fun toHashMap(): HashMap<String, Any?> {
        return hashMapOf(
            "cocktailId" to cocktailId,
            "name" to name,
            "category" to category,
            "imgUrl" to imgUrl,
            "instruction" to instruction,
            "ing1" to ing1,
            "ing2" to ing2,
            "ing3" to ing3,
            "ing4" to ing4,
            "ing5" to ing5,
            "ing6" to ing6,
            "ing7" to ing7,
            "ing8" to ing8,
            "ing9" to ing9,
            "ing10" to ing10,
            "ing11" to ing11,
            "ing12" to ing12,
            "ing13" to ing13,
            "ing14" to ing14,
            "ing15" to ing15,

            "meas1" to meas1,
            "meas2" to meas2,
            "meas3" to meas3,
            "meas4" to meas4,
            "meas5" to meas5,
            "meas6" to meas6,
            "meas7" to meas7,
            "meas8" to meas8,
            "meas9" to meas9,
            "meas10" to meas10,
            "meas11" to meas11,
            "meas12" to meas12,
            "meas13" to meas13,
            "meas14" to meas14,
            "meas5" to meas15,
        )
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(cocktailId)
        parcel.writeString(name)
        parcel.writeString(category)
        parcel.writeString(imgUrl)
        parcel.writeString(instruction)
        parcel.writeString(ing1)
        parcel.writeString(ing2)
        parcel.writeString(ing3)
        parcel.writeString(ing4)
        parcel.writeString(ing5)
        parcel.writeString(ing6)
        parcel.writeString(ing7)
        parcel.writeString(ing8)
        parcel.writeString(ing9)
        parcel.writeString(ing10)
        parcel.writeString(ing11)
        parcel.writeString(ing12)
        parcel.writeString(ing13)
        parcel.writeString(ing14)
        parcel.writeString(ing15)
        parcel.writeString(meas1)
        parcel.writeString(meas2)
        parcel.writeString(meas3)
        parcel.writeString(meas4)
        parcel.writeString(meas5)
        parcel.writeString(meas6)
        parcel.writeString(meas7)
        parcel.writeString(meas8)
        parcel.writeString(meas9)
        parcel.writeString(meas10)
        parcel.writeString(meas11)
        parcel.writeString(meas12)
        parcel.writeString(meas13)
        parcel.writeString(meas14)
        parcel.writeString(meas15)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CocktailModel> {
        override fun createFromParcel(parcel: Parcel): CocktailModel {
            return CocktailModel(parcel)
        }

        override fun newArray(size: Int): Array<CocktailModel?> {
            return arrayOfNulls(size)
        }
    }
}