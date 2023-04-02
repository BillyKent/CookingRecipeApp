package com.billysoft.domain.usecases

import com.billysoft.domain.model.Recipe
import com.billysoft.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetRecipes(
    private val repository: RecipeRepository,
) {

    operator fun invoke(): Flow<List<Recipe>> {
        return repository.getRecipes()
    }

}