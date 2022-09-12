package com.example.moviebuster.model


import com.google.gson.annotations.SerializedName

data class TPModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val topRatedList: List<TPList>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)