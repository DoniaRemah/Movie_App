package com.example.moviebuster.network

import com.example.moviebuster.model.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.Path

interface MovieApi {

    @GET("top_rated")
    suspend fun  getTopRatedMovies(@Query("api_key")key:String="fc47660226072874be57974ff797a0cd")
    : Response<TopandPopularModel>

    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key")key:String="fc47660226072874be57974ff797a0cd")
    : Response<TopandPopularModel>

    @GET("upcoming")
    suspend fun getUpcomingMovies(@Query("api_key")key:String="fc47660226072874be57974ff797a0cd")
    : Response<UpcomingModel>

    @GET("{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id")id:Int, @Query("api_key")key:String="fc47660226072874be57974ff797a0cd")
    : Response<MovieDetails>

}

