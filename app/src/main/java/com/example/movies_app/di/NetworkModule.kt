package com.example.movies_app.di

import com.example.movies_app.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideAccessToken(): String = BuildConfig.TMDB_ACCESS_TOKEN

    @Singleton
    @Provides
    fun provideAuthenticationHeaderInterceptor(
        accessToken: String
    ): AuthenticationHeadersInterceptor {
        return AuthenticationHeadersInterceptor(accessToken)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authenticationHeadersInterceptor: AuthenticationHeadersInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(authenticationHeadersInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json; charset=UTF8".toMediaType())
            ).build()
    }
}
