package com.example.moviebuster.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviebuster.model.*
import com.example.moviebuster.network.MovieApiClient
import com.example.moviebuster.network.RetrofitModule
import com.example.moviebuster.network.SimpleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  Repository {


    private  var topRatedList = mutableListOf<List<TopAndPopularList>>()
    val publicTopRatedList :  List <List<TopAndPopularList>> = topRatedList

    private var popList = mutableListOf<List<TopAndPopularList>>()
    val publicPopList : List<List<TopAndPopularList>> = popList

    private var upList = mutableListOf<List<UpcomingList>>()
    val publicUpList : List<List<UpcomingList>> = upList

    private var service = RetrofitModule.getInstance().service

    private val apiClient = MovieApiClient(service)

    suspend fun getTopRatedMovies(): SimpleResponse<TopandPopularModel>? {
        return apiClient.getTopRatedMovies()
    }

    suspend fun getPopularMovies():SimpleResponse<TopandPopularModel>?{
        return apiClient.getPopularMovies()
    }

    suspend fun getUpcomingMovies():SimpleResponse<UpcomingModel>?{
        return apiClient.getUpcomingMovies()
    }

    suspend fun getMovieDetails(id:Int):SimpleResponse<MovieDetails>?{
        return apiClient.getMovieDetails(id)
    }


}