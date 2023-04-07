package com.billysoft.domain.util

import com.billysoft.domain.model.Recipe
import com.billysoft.domain.model.RecipeOrigin
import java.util.*

object DataGenerator {

    fun createFakeReceiptData() = listOf(
        Recipe(
            id = "465c0a98-10ba-4997-87e4-82ef49489a2f",
            title = "Arroz chaufa",
            description = "El arroz chaufa, o chaufa, es un tipo de arroz frito consumido en Perú." +
                " Forma parte del estilo gastronómico tusán, siendo parte de la gastronomía del país, en la cual se denomina como cocina chifa.",
            photoUrl = "https://www.cocinista.es/download/bancorecursos/recetas/receta-arroz-chaufa-peruano.jpg",
            rating = 5.0f,
            cookingTime = 30,
            origin = RecipeOrigin(
                name = "Lima",
                country = "Perú",
                description = "Lima es la capital de Perú ubicada en la árida costa del Pacífico del país. Pese a que su centro colonial se conserva, es una desbordante metrópolis y una de las ciudades más grandes de Sudamérica. El Museo Larco alberga una colección de arte precolombino y el Museo de la Nación recorre la historia de las civilizaciones antiguas de Perú. La Plaza de Armas y la catedral del siglo XVI son el núcleo del antiguo centro de Lima.",
                latitude = -12.059782,
                longitude = -77.041074,
                referencePhotoUrl = "https://www.lima2019.pe/sites/default/files/2019-07/iStock-458584553.jpg",
                wikipediaUrl = "https://es.wikipedia.org/wiki/Lima",
            ),
            ingredients = listOf(
                "Arroz",
                "Pollo",
                "Ajo",
                "Salsa de soya",
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
            origin = RecipeOrigin(
                name = "Lima",
                country = "Perú",
                description = "Lima es la capital de Perú ubicada en la árida costa del Pacífico del país. Pese a que su centro colonial se conserva, es una desbordante metrópolis y una de las ciudades más grandes de Sudamérica. El Museo Larco alberga una colección de arte precolombino y el Museo de la Nación recorre la historia de las civilizaciones antiguas de Perú. La Plaza de Armas y la catedral del siglo XVI son el núcleo del antiguo centro de Lima.",
                latitude = -12.059782,
                longitude = -77.041074,
                referencePhotoUrl = "https://www.lima2019.pe/sites/default/files/2019-07/iStock-458584553.jpg",
                wikipediaUrl = "https://es.wikipedia.org/wiki/Lima",
            ),
            ingredients = listOf(
                "Tomate",
                "Arroz",
                "Carne",
                "Cebolla",
                "Tomates",
                "Cilantro",
                "Salsa de soya",
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
            origin = RecipeOrigin(
                name = "Chincha Alta, Ica",
                country = "Perú",
                description = "Santo Domingo de Chincha, denominada Chincha Alta para distinguirla de la Chincha Baja, es una ciudad peruana, capital del distrito homónimo y a la vez de la provincia de Chincha ubicada en el departamento de Ica. Se halla en la cuenca del río San Juan, a 200 km al sur de Lima. Tiene una superficie de 238,34 km².",
                latitude = -13.423964,
                longitude = -76.137956,
                referencePhotoUrl = "https://upload.wikimedia.org/wikipedia/commons/6/65/Hacienda_San_Jose%2C_Chincha.jpg",
                wikipediaUrl = "https://es.wikipedia.org/wiki/Provincia_de_Chincha",
            ),
            ingredients = listOf(
                "Arroz",
                "Papa",
                "Maní",
                "Ajo",
                "Cebolla",
            ),
        ),
    )

}