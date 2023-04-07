package com.billysoft.cookingrecipeapp

import com.billysoft.cookingrecipeapp.di.TestAppModule
import com.billysoft.cookingrecipeapp.ui.recipes.RecipeListViewModel
import com.billysoft.domain.usecases.RecipeUseCases
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(TestAppModule::class)
class RecipesScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var recipeUseCases: RecipeUseCases

    lateinit var viewModel: RecipeListViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = RecipeListViewModel(recipeUseCases)
    }

    @Test
    fun call_getRecipes_then_results_are_fetched() = runBlocking {
        val job = viewModel.getRecipesJob
        viewModel.onSearchTextChanged("")
        job?.join()
        delay(2000)
        val recipes = viewModel.recipeList.value
        assertNotNull(recipes)
    }
}