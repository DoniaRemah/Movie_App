package com.example.moviebuster.model

import com.google.gson.annotations.SerializedName

data class GeneralMoviesList (
    @SerializedName("id")
    val id: Int,
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)