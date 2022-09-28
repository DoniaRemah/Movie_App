package com.example.moviebuster.repository


import com.example.moviebuster.model.*
import com.example.moviebuster.network.MovieApiClient
import com.example.moviebuster.network.RetrofitModule
import com.example.moviebuster.network.SimpleResponse

class  Repository {




    private var service = RetrofitModule.getInstance().service

    private val apiClient = MovieApiClient(service)

    suspend fun getTopRatedMovies(): SimpleResponse<TopandPopularModel> {
        return apiClient.getTopRatedMovies()
    }

    suspend fun getPopularMovies():SimpleResponse<TopandPopularModel>{
        return apiClient.getPopularMovies()
    }

    suspend fun getUpcomingMovies():SimpleResponse<UpcomingModel>{
        return apiClient.getUpcomingMovies()
    }

    suspend fun getMovieDetails(id:Int):SimpleResponse<MovieDetails>{
        return apiClient.getMovieDetails(id)
    }


}