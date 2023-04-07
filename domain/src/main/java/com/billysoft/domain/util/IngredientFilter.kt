package com.billysoft.domain.util

sealed class IngredientFilter {

    class BySelectedIngredients(val ingredients: List<String>) : IngredientFilter()

    object NoFilter : IngredientFilter()

}