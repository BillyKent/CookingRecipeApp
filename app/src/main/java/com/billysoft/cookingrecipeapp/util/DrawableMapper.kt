package com.billysoft.cookingrecipeapp.util

import com.billysoft.cookingrecipeapp.R

class DrawableMapper {

    private val ingredientsDrawableMap: Map<String, Int> = mapOf(
        "Arroz" to R.drawable.ic_34_rice,
        "Pollo" to R.drawable.ic_151_roast_chicken,
        "Gallina" to R.drawable.ic_151_roast_chicken,
        "Ajo" to R.drawable.ic_garlic,
        "Salsa de soya" to R.drawable.ic_soy_sauce,
        "Sillao" to R.drawable.ic_soy_sauce,
        "Tomate" to R.drawable.ic_109_tomato,
        "Carne" to R.drawable.ic_216_steak,
        "Cordero" to R.drawable.ic_216_steak,
        "Cerdo" to R.drawable.ic_216_steak,
        "Corazón de res" to R.drawable.ic_216_steak,
        "Cebolla" to R.drawable.ic_230_onion,
        "Papa" to R.drawable.ic_249_potatoes,
        "Cilantro" to R.drawable.ic_coriander,
        "Culantro" to R.drawable.ic_coriander,
        "Maní" to R.drawable.ic_236_peanuts,
        "Ají panca" to R.drawable.chili,
        "Cuy" to R.drawable.guinea,
        "Ají" to R.drawable.chili,
        "Huacatay" to R.drawable.leaves,
        "Camote" to R.drawable.sweet_potato,
        "Choclo" to R.drawable.corn,
        "Vinagre" to R.drawable.balsamic_vinegar,
        "Aceite" to R.drawable.cooking,
        "Pimienta" to R.drawable.chili_pepper,
        "Sal" to R.drawable.salt_shaker,
        "Comino" to R.drawable.cumin,
        "Rocoto" to R.drawable.rocoto,
        "Carne molida" to R.drawable.minced_meat,
        "Huevo" to R.drawable.egg,
        "Queso" to R.drawable.cheese,
        "Leche" to R.drawable.milk,
        "Orégano" to R.drawable.oregano,
        "Plátano verde" to R.drawable.green_banana,
        "Manteca" to R.drawable.lard,
        "Cecina de cerdo" to R.drawable.pork,
        "Chorizo" to R.drawable.pepperoni,
        "Aceituna" to R.drawable.olives,
        "Laurel" to R.drawable.bay,
        "Manteca de cerdo" to R.drawable.lard,
        "Trigo" to R.drawable.grain,
        "Garbanzo" to R.drawable.chickpeas,
        "Zarandaja" to R.drawable.red_beans,
        "Habas" to R.drawable.edamame,
        "Panceta de cerdo" to R.drawable.pork,
        "Costilla de cerdo" to R.drawable.ribs,
        "Ají amarillo" to R.drawable.chili_pepper,
        "Ají mirasol" to R.drawable.chili,
        "Galletas de agua" to R.drawable.cookies,
        "Lechuga" to R.drawable.lettuce,
        "Paico" to R.drawable.leaves,
    )

    fun getIngredientDrawable(code: String) =
        ingredientsDrawableMap[code] ?: R.drawable.ic_vegetable

}