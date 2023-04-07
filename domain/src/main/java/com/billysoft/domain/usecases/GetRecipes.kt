package com.billysoft.domain.usecases

import com.billysoft.domain.model.Recipe
import com.billysoft.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetRecipes(
    private val repository: RecipeRepository,
) {

    operator fun invoke(
        keyword: String = String()
    ): Flow<List<Recipe>> {
        return repository.getRecipes().map { recipes ->

            if (keyword.isBlank()) {
                return@map recipes
            }

            recipes.filter { recipe ->
                recipe.title.contains(keyword, true) || recipe.ingredients.any { ingredient ->
                    ingredient.split(" ").any { it.startsWith(keyword, true) }
                }
            }

        }.flowOn(Dispatchers.Default)
    }

}