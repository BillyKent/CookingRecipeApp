package com.billysoft.domain.model

data class RecipeOrigin(
    val name: String,
    val country: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val referencePhotoUrl: String,
    val wikipediaUrl: String,
)