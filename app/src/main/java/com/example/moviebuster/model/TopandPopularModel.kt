package com.example.moviebuster.model


import com.google.gson.annotations.SerializedName

data class TopandPopularModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val topAndPopularList_model: List<TopAndPopularList>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)