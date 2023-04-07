package com.billysoft.cookingrecipeapp.di.repository

import com.billysoft.domain.model.Recipe
import com.billysoft.domain.repository.RecipeRepository
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