package com.billysoft.domain.usecases

import com.billysoft.domain.model.Recipe
import com.billysoft.domain.repository.RecipeRepository
import com.billysoft.domain.util.IngredientFilter
import com.billysoft.domain.util.QueryFilter
import com.billysoft.domain.util.RecipeFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetRecipes(
    private val repository: RecipeRepository,
) {

    operator fun invoke(
        recipeFilter: RecipeFilter = RecipeFilter.DEFAULT
    ): Flow<List<Recipe>> {
        return repository.getRecipes().map { recipes ->

            if (recipeFilter == RecipeFilter.DEFAULT) {
                return@map recipes
            }

            recipes
                .filter { recipe ->
                    when (val filter = recipeFilter.queryFilter) {
                        is QueryFilter.ByKeyword -> {
                            if (filter.keyword.isNotBlank()) {
                                recipe.title.contains(filter.keyword, true)
                            } else {
                                true
                            }
                        }
                        QueryFilter.NoFilter -> true
                    }
                }
                .filter { recipe ->
                    when (val filter = recipeFilter.ingredientFilter) {
                        is IngredientFilter.BySelectedIngredients -> {
                            if (filter.ingredients.isNotEmpty()) {
                                recipe.ingredients.any { receiptIngredient ->
                                    filter.ingredients.contains(
                                        receiptIngredient
                                    )
                                }
                            } else {
                                true
                            }
                        }
                        IngredientFilter.NoFilter -> true
                    }
                }

        }.flowOn(Dispatchers.Default)
    }

}