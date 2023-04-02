package com.billysoft.domain.usecases

import com.billysoft.domain.usecases.GetRecipe
import com.billysoft.domain.usecases.GetRecipes

data class RecipeUseCases(
    val getRecipe: GetRecipe,
    val getRecipes: GetRecipes,
)