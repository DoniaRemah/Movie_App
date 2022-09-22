package com.example.moviebuster.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuster.R
import com.example.moviebuster.databinding.ItemMovieBinding
import com.example.moviebuster.model.UpcomingList
import com.squareup.picasso.Picasso

class UpcomingAdapter: RecyclerView.Adapter<UpcomingAdapter.MovieItemViewHolder>(){

    private var movies: MutableList<UpcomingList> = ArrayList()
    class MovieItemViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)
    var onMovieClickListener: UpcomingAdapter.OnMovieClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingAdapter.MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context)

        val binding: ItemMovieBinding = DataBindingUtil.inflate(view!!,
            R.layout.item_movie,parent,false)

        return UpcomingAdapter.MovieItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: UpcomingAdapter.MovieItemViewHolder, position: Int) {
        val item = movies[position]

        var pictureUrl = "https://www.themoviedb.org/t/p/original"+item.posterPath
        Picasso.with(holder.binding.PosterImage.context)
            .load(pictureUrl)
            .into(holder.binding.PosterImage)

        holder.binding.PosterImage.setOnClickListener {

            val index = movies.indexOf(item)
            onMovieClickListener?.onMovieClicked(movies[index].id)
        }

    }
    fun submitList(newItems: List<UpcomingList>?) {
        clear()
        newItems?.let {
            movies.addAll(it)
            notifyDataSetChanged()
        }
    }



    @Suppress("unused")
    fun clear() {
        movies.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    interface OnMovieClickListener {
        fun onMovieClicked(movieId: Int)
    }

    }




