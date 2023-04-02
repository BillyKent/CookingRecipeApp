package com.billysoft.domain.usecases

import com.billysoft.domain.model.Recipe
import com.billysoft.domain.repository.RecipeRepository

class GetRecipe(
    private val repository: RecipeRepository,
) {

    suspend operator fun invoke(id: String): Recipe? {
        return repository.getRecipeById(id)
    }
}