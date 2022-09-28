package com.example.moviebuster.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuster.databinding.ItemMovieBinding
import com.example.moviebuster.model.GeneralMovieDetails
import com.squareup.picasso.Picasso

class MoviesAdapter:RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var onMovieClickInt: OnMovieClick? = null

    private var movieDifferCallback = object : DiffUtil.ItemCallback<GeneralMovieDetails>(){
        override fun areItemsTheSame(
            oldItem: GeneralMovieDetails,
            newItem: GeneralMovieDetails
        ): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GeneralMovieDetails,
            newItem: GeneralMovieDetails
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val movieDiffer = AsyncListDiffer(this,movieDifferCallback)

    inner class MovieViewHolder(val binding:ItemMovieBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       return MovieViewHolder(
           ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false ))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = movieDiffer.currentList[position]
        val pictureUrl = "https://www.themoviedb.org/t/p/original"+movieItem.posterPath

        Picasso.with(holder.binding.PosterImage.context)
            .load(pictureUrl)
            .into(holder.binding.PosterImage)

        //Setting on CLick Listener
        holder.binding.PosterImage.setOnClickListener {
            val index = movieDiffer.currentList.indexOf(movieItem)
            // This is an instance of the interface.
            onMovieClickInt?.movieClicked(movieDiffer.currentList[index].id)
        }
    }


    override fun getItemCount(): Int {
       return movieDiffer.currentList.size
    }

    interface OnMovieClick {
        fun movieClicked(movieId: Int)
    }

    fun submitList(list: List<GeneralMovieDetails>) {
        movieDiffer.submitList(list)
    }
}