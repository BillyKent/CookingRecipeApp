package com.billysoft.data.di

import com.billysoft.data.repository.FakeRecipeRepositoryImpl
import com.billysoft.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(): RecipeRepository {
        return FakeRecipeRepositoryImpl()
    }

}