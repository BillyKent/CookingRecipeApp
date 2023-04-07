package com.billysoft.data.network

import com.billysoft.data.network.response.RecipeResponse
import retrofit2.http.GET

internal interface ApiService {

    @GET("recipsaes")
    suspend fun getRecipes(): List<RecipeResponse>

}