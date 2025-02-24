package com.example.movies_app.topratedmovies.remote

import com.example.movies_app.topratedmovies.data.TopRatedMoviesApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedMoviesApiService {
    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(@Query("page") page: Int): TopRatedMoviesApiModel
}
