package com.billysoft.domain.usecases

import com.billysoft.domain.repository.FakeRecipeRepository
import com.billysoft.domain.repository.RecipeRepository
import com.billysoft.domain.util.DataGenerator
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test

class GetRecipeTest {

    private lateinit var getRecipe: GetRecipe
    private lateinit var fakeRepository: RecipeRepository

    @Before
    fun setUp() {
        val fakeList = DataGenerator.createFakeReceiptData()
        fakeRepository = FakeRecipeRepository(fakeList)
        getRecipe = GetRecipe(fakeRepository)
    }

    @Test
    fun `get recipe with known id, get result`() = runBlocking {
        val recipe = getRecipe("465c0a98-10ba-4997-87e4-82ef49489a2f")
        MatcherAssert.assertThat(recipe, notNullValue())
    }

    @Test
    fun `get recipe with unknown id, get null`() = runBlocking {
        val recipe = getRecipe("465c0a98")
        MatcherAssert.assertThat(recipe, nullValue())
    }

}