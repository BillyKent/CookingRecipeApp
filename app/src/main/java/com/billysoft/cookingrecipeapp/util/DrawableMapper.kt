package com.billysoft.cookingrecipeapp.util

import com.billysoft.cookingrecipeapp.R

class DrawableMapper {

    private val ingredientsDrawableMap: Map<String, Int> = mapOf(
        "rice" to R.drawable.ic_34_rice,
        "roast_chicken" to R.drawable.ic_151_roast_chicken,
        "garlic" to R.drawable.ic_garlic,
        "soy_sauce" to R.drawable.ic_soy_sauce,
        "tomato" to R.drawable.ic_109_tomato,
        "steak" to R.drawable.ic_216_steak,
        "onion" to R.drawable.ic_230_onion,
        "potatoes" to R.drawable.ic_249_potatoes,
        "coriander" to R.drawable.ic_coriander,
        "peanuts" to R.drawable.ic_236_peanuts,
    )

    fun getIngredientDrawable(code: String) =
        ingredientsDrawableMap[code] ?: R.drawable.ic_vegetable

}