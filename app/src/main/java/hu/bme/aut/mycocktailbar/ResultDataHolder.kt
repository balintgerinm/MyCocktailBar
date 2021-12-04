package hu.bme.aut.mycocktailbar

import hu.bme.aut.mycocktailbar.model.ResultModel

interface ResultDataHolder {
    fun getResultData(): ResultModel?
}
