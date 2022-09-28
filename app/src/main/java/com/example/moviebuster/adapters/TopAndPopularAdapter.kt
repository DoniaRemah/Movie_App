package com.example.moviebuster.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuster.R
import com.example.moviebuster.model.TopAndPopularList
import com.example.moviebuster.databinding.ItemMovieBinding
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso


class TopAndPopularAdapter: RecyclerView.Adapter<TopAndPopularAdapter.MovieItemViewHolder>()
{

    var onMovieClickListener: OnMovieClickListener? = null

    private var movies: MutableList<TopAndPopularList> = ArrayList()
    // A view Holder class that takes the binding class of the views and passes its root to the rv.
    class MovieItemViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    // This class is called each time a new view is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context)

        val binding: ItemMovieBinding = DataBindingUtil.inflate(view!!,R.layout.item_movie,parent,false)

        return MovieItemViewHolder(binding)
    }

    // This class is called to bind the data to the views.
    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val item = movies[position]

        val pictureUrl = "https://www.themoviedb.org/t/p/original"+item.posterPath


        Picasso.with(holder.binding.PosterImage.context)
            .load(pictureUrl)
            .into(holder.binding.PosterImage)

        holder.binding.PosterImage.setOnClickListener {
            val index = movies.indexOf(item)
            // This is an instance of the interface.
            onMovieClickListener?.onMovieClicked(movies[index].id)
        }

    }



     fun submitList(newItems: List<TopAndPopularList>?) {
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


