package com.billysoft.domain.util

sealed class RecipeFilter {

    class Name(name: String) : RecipeFilter()

    class Ingredients(ingredients: List<String>) : RecipeFilter()

}