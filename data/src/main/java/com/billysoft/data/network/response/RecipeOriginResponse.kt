package com.billysoft.data.network.response

import com.billysoft.domain.model.RecipeOrigin
import com.google.gson.annotations.SerializedName

internal data class RecipeOriginResponse(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("description") val description: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("reference_photo_url") val referencePhotoUrl: String,
    @SerializedName("wikipedia_url") val wikipediaUrl: String,
) {

    fun toDomainModel() = RecipeOrigin(
        name, country, description, latitude, longitude, referencePhotoUrl, wikipediaUrl
    )

}