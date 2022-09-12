package com.example.moviebuster.network

import com.example.moviebuster.model.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Path

interface MovieApi {

    @GET("top_rated")
    fun  getTopRatedMovies(@Query("api_key")key:String): Call<TPModel>

    @GET("popular")
    fun getPopularMovies(@Query("api_key")key:String): Call<TPModel>

    @GET("upcoming")
    fun getUpcomingMovies(@Query("api_key")key:String): Call<UpcomingModel>

    @GET("{movie_id}")
    fun getMovieDetails(@Path("movie_id")id:Int, @Query("api_key")key:String): Call<MovieDetails>

}

