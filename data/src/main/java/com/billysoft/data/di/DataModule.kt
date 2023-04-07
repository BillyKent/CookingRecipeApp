package com.billysoft.data.di

import com.billysoft.data.network.ApiService
import com.billysoft.data.network.ConnectivityManager
import com.billysoft.data.repository.RecipeRepositoryImpl
import com.billysoft.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(
        apiService: ApiService,
        connectivityManager: ConnectivityManager,
    ): RecipeRepository {
        return RecipeRepositoryImpl(apiService, connectivityManager)
    }

}