package com.billysoft.cookingrecipeapp.util

import com.billysoft.cookingrecipeapp.R

class DrawableMapper {

    private val ingredientsDrawableMap: Map<String, Int> = mapOf(
        "Arroz" to R.drawable.ic_34_rice,
        "Pollo" to R.drawable.ic_151_roast_chicken,
        "Ajo" to R.drawable.ic_garlic,
        "Salsa de soya" to R.drawable.ic_soy_sauce,
        "Sillao" to R.drawable.ic_soy_sauce,
        "Tomate" to R.drawable.ic_109_tomato,
        "Carne" to R.drawable.ic_216_steak,
        "Cebolla" to R.drawable.ic_230_onion,
        "Papa" to R.drawable.ic_249_potatoes,
        "Cilantro" to R.drawable.ic_coriander,
        "Maní" to R.drawable.ic_236_peanuts,
    )

    fun getIngredientDrawable(code: String) =
        ingredientsDrawableMap[code] ?: R.drawable.ic_vegetable

}