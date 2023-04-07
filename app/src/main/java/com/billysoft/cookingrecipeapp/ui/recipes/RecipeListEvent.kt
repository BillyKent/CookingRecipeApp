package com.billysoft.cookingrecipeapp.ui.recipes

sealed class RecipeListEvent {

    data class SetKeywordFilter(
        val keyword: String
    ) : RecipeListEvent()

    data class SetIngredientsFilter(
        val ingredients: List<String>
    ) : RecipeListEvent()

    object ClearTextQuery : RecipeListEvent()

    object ClearIngredientsFilter : RecipeListEvent()

}