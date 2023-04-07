package com.billysoft.data.repository

import com.billysoft.data.network.ApiService
import com.billysoft.data.network.ConnectivityManager
import com.billysoft.data.network.response.toDomainModel
import com.billysoft.domain.model.Recipe
import com.billysoft.domain.model.exceptions.ApiException
import com.billysoft.domain.model.exceptions.NetworkException
import com.billysoft.domain.model.exceptions.UnknownException
import com.billysoft.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

internal class RecipeRepositoryImpl(
    private val apiService: ApiService,
    private val connectivityManager: ConnectivityManager,
) : RecipeRepository {

    private val inMemoryRecipeMap = mutableMapOf<String, Recipe>()

    override fun getRecipes(): Flow<List<Recipe>> = flow {
        if (inMemoryRecipeMap.isEmpty()) {
            val recipesFromServer = apiService.getRecipes().toDomainModel()
            inMemoryRecipeMap.putAll(recipesFromServer.associateBy(Recipe::id))
        }
        emit(inMemoryRecipeMap.values.toList())
    }
        .catch { exception ->
            when (exception) {
                is HttpException -> {
                    when (exception.code()) {
                        404, 500 -> throw ApiException()
                        else -> throw UnknownException(exception.message())
                    }
                }
                is IOException -> {
                    if (connectivityManager.isNetworkAvailable()) {
                        throw ApiException()
                    } else {
                        throw NetworkException()
                    }

                }
                else -> throw UnknownException(exception.message)
            }

        }
        .flowOn(Dispatchers.IO)

    override suspend fun getRecipeById(id: String): Recipe? {
        return inMemoryRecipeMap[id]
    }
}