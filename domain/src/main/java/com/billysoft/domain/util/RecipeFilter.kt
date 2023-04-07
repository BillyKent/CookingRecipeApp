package com.billysoft.domain.util

data class RecipeFilter(
    val queryFilter: QueryFilter = QueryFilter.NoFilter,
    val ingredientFilter: IngredientFilter = IngredientFilter.NoFilter,
) {
    companion object {
        val DEFAULT = RecipeFilter()
    }
}