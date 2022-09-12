package com.example.moviebuster.views


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuster.R
import com.example.moviebuster.model.TPList
import com.example.moviebuster.databinding.ItemMovieBinding
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso


class TPAdapter(): RecyclerView.Adapter<TPAdapter.MovieItemViewHolder>()
{

    var onMovieClickListener: OnMovieClickListener? = null

    private var movies: MutableList<TPList> = ArrayList()
    class MovieItemViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context)

        val binding: ItemMovieBinding = DataBindingUtil.inflate(view!!,R.layout.item_movie,parent,false)

        return MovieItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val item = movies[position]
        //holder.binding.movieName.text = item.title.toString()


        var pictureUrl = "https://www.themoviedb.org/t/p/w220_and_h330_face"+item.posterPath
        Picasso.with(holder.binding.PosterImage.context)
            .load(pictureUrl)
            .into(holder.binding.PosterImage)

        holder.binding.PosterImage.setOnClickListener {

            val index = movies.indexOf(item)
            onMovieClickListener?.onMovieClicked(movies[index].id)
        }

    }

    fun submitListAct2(newItems: List<TPList>?){
        clear()
        newItems?.let {
            movies.addAll(it)
        }
    }


    fun submitList(newItems: List<TPList>?) {
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


