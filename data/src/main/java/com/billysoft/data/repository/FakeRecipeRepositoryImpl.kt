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
            UUID.randomUUID().toString(),
            "Arroz chaufa",
            "El arroz chaufa, o chaufa, es un tipo de arroz frito consumido en Perú." +
                " Forma parte del estilo gastronómico tusán, siendo parte de la gastronomía del país, en la cual se denomina como cocina chifa.",
            "https://www.cocinista.es/download/bancorecursos/recetas/receta-arroz-chaufa-peruano.jpg",
            RecipeOrigin("Perú", -12.059782, -77.041074)
        ),
        Recipe(
            UUID.randomUUID().toString(),
            "Lomo saltado",
            "El lomo saltado es un plato típico de la gastronomía del Perú consistente en carne de res, arroz cocido y papas fritas." +
                " Es uno de los platos más consumidos popularmente en el Perú.",
            "https://i.blogs.es/5620ff/lomo-saltado-dap/450_1000.jpg",
            RecipeOrigin("Perú", -12.059782, -77.041074)
        ),
        Recipe(
            UUID.randomUUID().toString(),
            "Carapulcra",
            "La carapulcra es un guiso típico de la gastronomía del Perú." +
                " Es un plato ligado al mestizaje de las culturas culinaria indígena y afroperuana," +
                " uno de los más antiguos de los que se tiene noticia en el país andino." +
                " Los ingredientes principales de la carapulcra son papa, seca o fresca, maní, ají colorado y otras especias," +
                " así como diversos tipos de carne.",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/Carapulcra.jpg/800px-Carapulcra.jpg",
            RecipeOrigin("Chincha, Ica", -13.423964, -76.137956)
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