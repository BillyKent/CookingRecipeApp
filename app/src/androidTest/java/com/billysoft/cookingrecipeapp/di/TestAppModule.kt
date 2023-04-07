package com.billysoft.cookingrecipeapp.di

import com.billysoft.cookingrecipeapp.di.repository.FakeRecipeRepository
import com.billysoft.cookingrecipeapp.di.util.DataGenerator
import com.billysoft.domain.repository.RecipeRepository
import com.billysoft.domain.usecases.GetRecipe
import com.billysoft.domain.usecases.GetRecipes
import com.billysoft.domain.usecases.RecipeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideRecipeUseCases(): RecipeUseCases {
        val fakeRepository = FakeRecipeRepository(DataGenerator.createFakeReceiptData())
        return RecipeUseCases(
            GetRecipe(fakeRepository),
            GetRecipes(fakeRepository),
        )
    }

}