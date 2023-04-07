package com.billysoft.domain.repository

import com.billysoft.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRecipeRepository(private val fakeList: List<Recipe>) : RecipeRepository {

    override fun getRecipes(): Flow<List<Recipe>> {
        return flow {
            emit(fakeList)
        }
    }

    override suspend fun getRecipeById(id: String): Recipe? {
        return fakeList.find { it.id == id }
    }
}