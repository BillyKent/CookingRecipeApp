package com.billysoft.data.repository

import com.billysoft.data.network.ApiService
import com.billysoft.data.network.response.toDomainModel
import com.billysoft.domain.model.Recipe
import com.billysoft.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class RecipeRepositoryImpl(
    private val apiService: ApiService,
) : RecipeRepository {

    private val inMemoryRecipeList = mutableListOf<Recipe>()

    override fun getRecipes(): Flow<List<Recipe>> = flow {
        inMemoryRecipeList.clear()
        val recipesFromServer = apiService.getRecipes().toDomainModel()
        inMemoryRecipeList.addAll(recipesFromServer)
        emit(inMemoryRecipeList)
    }

    override suspend fun getRecipeById(id: String): Recipe? {
        return inMemoryRecipeList.find { it.id == id }
    }
}