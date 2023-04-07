package com.billysoft.domain.util

data class RecipeFilter(
    var queryFilter: QueryFilter = QueryFilter.NoFilter,
    var ingredientFilter: IngredientFilter = IngredientFilter.NoFilter,
) {
    companion object {
        val DEFAULT = RecipeFilter()
    }
}