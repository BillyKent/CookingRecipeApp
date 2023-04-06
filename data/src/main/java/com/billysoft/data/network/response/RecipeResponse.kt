package com.billysoft.data.network.response

import com.billysoft.domain.model.Recipe
import com.google.gson.annotations.SerializedName

internal data class RecipeResponse(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("photo_url") val photoUrl: String,
    @SerializedName("rating") val rating: Float,
    @SerializedName("cooking_time") val cookingTime: Int,
    @SerializedName("origin") val origin: RecipeOriginResponse,
    @SerializedName("ingredients") val ingredients: List<String>,
) {

    fun toDomainModel() = Recipe(
        id, title, description, photoUrl, rating, cookingTime, origin.toDomainModel(), ingredients
    )

}

internal fun List<RecipeResponse>.toDomainModel() = map { it.toDomainModel() }