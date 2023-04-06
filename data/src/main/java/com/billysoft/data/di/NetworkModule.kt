package com.billysoft.data.di

import com.billysoft.data.network.ApiService
import com.billysoft.data.util.DataModuleConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun providesOkhttpClient(
        loggingInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
        .connectTimeout(DataModuleConstants.CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .readTimeout(DataModuleConstants.CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS).build()

    @Singleton
    @Provides
    fun providesGson(): Gson =
        GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().setVersion(1.0)
            .create()

    @Provides
    @Singleton
    fun provideApiService(gson: Gson, client: OkHttpClient): ApiService =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(DataModuleConstants.BASE_URL).client(client).build()
            .create(ApiService::class.java)

}