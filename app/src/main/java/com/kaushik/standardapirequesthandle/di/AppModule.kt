package com.kaushik.standardapirequesthandle.di

import com.kaushik.standardapirequesthandle.Constants
import com.kaushik.standardapirequesthandle.main.MainRepository
import com.kaushik.standardapirequesthandle.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
val okHttp = OkHttpClient.Builder().addInterceptor(logger)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePostApiService(): ApiService = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
        .build()
        .create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(apiService: ApiService): MainRepository = MainRepository(apiService)

}