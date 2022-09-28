package com.example.moviebuster.model

import com.google.gson.annotations.SerializedName

data class GeneralMovies(
    @SerializedName("results")
    val movieList: List<GeneralMovieDetails>
)
