package com.example.movies_app.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationHeadersInterceptor @Inject constructor(
    private val accessToken: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
        return chain.proceed(request)
    }
}
