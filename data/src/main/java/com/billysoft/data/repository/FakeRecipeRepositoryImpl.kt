package com.billysoft.data.repository

import com.billysoft.domain.model.Recipe
import com.billysoft.domain.model.RecipeOrigin
import com.billysoft.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

internal class FakeRecipeRepositoryImpl : RecipeRepository {

    private val fakeList: List<Recipe> = listOf(
        Recipe(
            id = UUID.randomUUID().toString(),
            title = "Arroz chaufa",
            description = "El arroz chaufa, o chaufa, es un tipo de arroz frito consumido en Perú." +
                " Forma parte del estilo gastronómico tusán, siendo parte de la gastronomía del país, en la cual se denomina como cocina chifa.",
            photoUrl = "https://www.cocinista.es/download/bancorecursos/recetas/receta-arroz-chaufa-peruano.jpg",
            rating = 5.0f,
            cookingTime = 30,
            origin = RecipeOrigin("Perú", -12.059782, -77.041074),
            ingredients = listOf(
                "rice",
                "roast_chicken",
                "garlic",
                "soy_sauce",
            ),
        ),
        Recipe(
            id = UUID.randomUUID().toString(),
            title = "Lomo saltado",
            description = "El lomo saltado es un plato típico de la gastronomía del Perú consistente en carne de res, arroz cocido y papas fritas." +
                " Es uno de los platos más consumidos popularmente en el Perú.",
            photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Lomo-saltado-perudelights.jpg/640px-Lomo-saltado-perudelights.jpg",
            rating = 3.5f,
            cookingTime = 50,
            origin = RecipeOrigin("Perú", -12.059782, -77.041074),
            ingredients = listOf(
                "tomato",
                "rice",
                "steak",
                "onion",
                "potatoes",
                "coriander",
                "soy_sauce",
            ),
        ),
        Recipe(
            id = UUID.randomUUID().toString(),
            title = "Carapulcra",
            description = "La carapulcra es un guiso típico de la gastronomía del Perú." +
                " Es un plato ligado al mestizaje de las culturas culinaria indígena y afroperuana," +
                " uno de los más antiguos de los que se tiene noticia en el país andino." +
                " Los ingredientes principales de la carapulcra son papa, seca o fresca, maní, ají colorado y otras especias," +
                " así como diversos tipos de carne.",
            photoUrl = "https://www.comedera.com/wp-content/uploads/2021/08/carapulcra-peruana.jpg",
            rating = 4.0f,
            cookingTime = 35,
            origin = RecipeOrigin("Chincha, Ica", -13.423964, -76.137956),
            ingredients = listOf(
                "rice",
                "potatoes",
                "peanuts",
                "garlic",
                "onion",
            ),
        ),
    )

    override fun getRecipes(): Flow<List<Recipe>> {
        return flow {
            emit(fakeList)
        }
    }

    override suspend fun getRecipeById(id: String): Recipe? {
        return fakeList.find { it.id == id }
    }
}