package com.billysoft.cookingrecipeapp.di

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
object AppModule {

    @Provides
    @Singleton
    fun provideRecipeUseCases(repository: RecipeRepository): RecipeUseCases {
        return RecipeUseCases(
            GetRecipe(repository),
            GetRecipes(repository),
        )
    }

}