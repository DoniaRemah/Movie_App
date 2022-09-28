package com.example.moviebuster.repository


import com.example.moviebuster.model.*
import com.example.moviebuster.network.MovieApiClient
import com.example.moviebuster.network.RetrofitModule
import com.example.moviebuster.network.SimpleResponse

class  Repository {




    private var service = RetrofitModule.getInstance().service

    private val apiClient = MovieApiClient(service)

    suspend fun getTopRatedMovies(): SimpleResponse<GeneralMovies> {
        return apiClient.getTopRatedMovies()
    }

    suspend fun getPopularMovies():SimpleResponse<GeneralMovies>{
        return apiClient.getPopularMovies()
    }

    suspend fun getUpcomingMovies():SimpleResponse<GeneralMovies>{
        return apiClient.getUpcomingMovies()
    }

    suspend fun getMovieDetails(id:Int):SimpleResponse<GeneralMovieDetails>{
        return apiClient.getMovieDetails(id)
    }


}