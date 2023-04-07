package com.billysoft.domain.usecases

import com.billysoft.domain.repository.FakeRecipeRepository
import com.billysoft.domain.repository.RecipeRepository
import com.billysoft.domain.util.DataGenerator
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test

class GetRecipesTest {

    private lateinit var getRecipes: GetRecipes
    private lateinit var fakeRepository: RecipeRepository

    @Before
    fun setUp() {
        val fakeList = DataGenerator.createFakeReceiptData()
        fakeRepository = FakeRecipeRepository(fakeList)
        getRecipes = GetRecipes(fakeRepository)
    }

    @Test
    fun `Filter receipts by blank keyword, get all results`() = runBlocking {
        val recipes = getRecipes(" ").first()
        assertThat(recipes, not(empty()))
        assertThat(recipes.size, greaterThan(1))
    }

    @Test
    fun `Filter receipts by empty string, all results`() = runBlocking {
        val recipes = getRecipes("").first()
        assertThat(recipes, not(empty()))
        assertThat(recipes.size, greaterThan(1))
    }

    @Test
    fun `Filter by known keyword, get result`() = runBlocking {
        val knownKeyword = "Lomo"
        val recipes = getRecipes(knownKeyword).first()

        assertThat(recipes, not(empty()))
        assertThat(recipes.size, equalTo(1))
    }

    @Test
    fun `Filter by unknown keyword, get empty result`() = runBlocking {
        val knownKeyword = "Lomas"
        val recipes = getRecipes(knownKeyword).first()

        assertThat(recipes, empty())
    }

}