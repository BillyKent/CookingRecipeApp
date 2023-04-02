package com.billysoft.domain.model

data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val photoUrl: String,
    val origin: RecipeOrigin,
)