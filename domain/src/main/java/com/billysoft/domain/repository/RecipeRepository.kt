package com.billysoft.domain.repository

import com.billysoft.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getRecipes(): Flow<List<Recipe>>

    suspend fun getRecipeById(id: String): Recipe?

}