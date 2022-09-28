package com.example.moviebuster.network

import com.example.moviebuster.model.GeneralMovieDetails
import com.example.moviebuster.model.GeneralMovies
import retrofit2.Response

class MovieApiClient(private val movieApi: MovieApi) {

    suspend fun getTopRatedMovies(): SimpleResponse<GeneralMovies> {

        return safeApiCall{movieApi.getTopRatedMovies()}
    }

    suspend fun getPopularMovies():SimpleResponse<GeneralMovies>{
        return safeApiCall { movieApi.getPopularMovies() }
    }

    suspend fun getUpcomingMovies():SimpleResponse<GeneralMovies>{
        return safeApiCall { movieApi.getUpcomingMovies() }
    }

    suspend fun getMovieDetails(id:Int):SimpleResponse<GeneralMovieDetails>{
        return safeApiCall {
            movieApi.getMovieDetails(id)
        }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }


}