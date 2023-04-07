package com.billysoft.data.repository

import com.billysoft.data.network.ApiService
import com.billysoft.data.network.response.toDomainModel
import com.billysoft.domain.model.Recipe
import com.billysoft.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class RecipeRepositoryImpl(
    private val apiService: ApiService,
) : RecipeRepository {

    private val inMemoryRecipeMap = mutableMapOf<String, Recipe>()

    override fun getRecipes(): Flow<List<Recipe>> = flow {
        if (inMemoryRecipeMap.isEmpty()) {
            val recipesFromServer = apiService.getRecipes().toDomainModel()
            inMemoryRecipeMap.putAll(recipesFromServer.associateBy(Recipe::id))
        }
        emit(inMemoryRecipeMap.values.toList())
    }.flowOn(Dispatchers.IO)

    override suspend fun getRecipeById(id: String): Recipe? {
        return inMemoryRecipeMap[id]
    }
}