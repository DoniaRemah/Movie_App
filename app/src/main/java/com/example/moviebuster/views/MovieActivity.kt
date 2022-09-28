package com.example.moviebuster.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviebuster.R
import com.example.moviebuster.databinding.ActivityMovieBinding
import com.example.moviebuster.viewmodels.MovieViewModel
import com.squareup.picasso.Picasso

class MovieActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMovieBinding
    private lateinit var poster:ImageView
    private lateinit var titleTv:TextView
    private lateinit var descTv:TextView
    private lateinit var rDate:TextView
    private lateinit var vote:TextView
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie)

        // Linking View Model
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]


        val myMovieId = intent.getIntExtra("EXTRA_TPMovieId",0)

        linkToViews()

        viewModel.getMovieDetails(myMovieId)

        settingObservers()

    }

    private fun settingObservers(){
        viewModel.myMovie.observe(this) { myMovie ->

            if (myMovie == null) {
                Toast.makeText(
                    this, "Network Error Getting Movie",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                val pictureUrl = "https://www.themoviedb.org/t/p/original" + myMovie.posterPath
                Picasso.with(this)
                    .load(pictureUrl)
                    .into(poster)

                titleTv.text = myMovie.title
                descTv.text = myMovie.overview
                rDate.text = myMovie.releaseDate
                vote.text = myMovie.voteAverage.toString()
            }
        }
    }

    private fun linkToViews(){
        poster= binding.moviePoster
        titleTv= binding.movieTitle
        descTv= binding.DescText
        rDate = binding.rDateTV
        vote = binding.voteValueTv
    }

    }


