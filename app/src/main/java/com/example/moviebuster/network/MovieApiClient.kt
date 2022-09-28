package com.example.moviebuster.network

import com.example.moviebuster.model.MovieDetails
import com.example.moviebuster.model.TopandPopularModel
import com.example.moviebuster.model.UpcomingModel
import retrofit2.Response

class MovieApiClient(private val movieApi: MovieApi) {

    suspend fun getTopRatedMovies(): SimpleResponse<TopandPopularModel> {

        return safeApiCall{movieApi.getTopRatedMovies()}
    }

    suspend fun getPopularMovies():SimpleResponse<TopandPopularModel>{
        return safeApiCall { movieApi.getPopularMovies() }
    }

    suspend fun getUpcomingMovies():SimpleResponse<UpcomingModel>{
        return safeApiCall { movieApi.getUpcomingMovies() }
    }

    suspend fun getMovieDetails(id:Int):SimpleResponse<MovieDetails>{
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