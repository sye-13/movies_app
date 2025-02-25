package com.example.movies_app.movie.remote

import com.example.movies_app.movie.data.MovieApiModel
import com.example.movies_app.movie.data.TopRatedMoviesApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(@Query("page") page: Int): TopRatedMoviesApiModel

    @GET("movie/{id}")
    suspend fun fetchMovie(@Path("id") id: String): MovieApiModel
}
