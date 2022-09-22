package com.example.moviebuster.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviebuster.R
import com.example.moviebuster.databinding.ActivityMovieBinding
import com.example.moviebuster.model.*
import com.example.moviebuster.network.RetrofitModule
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMovieBinding
    lateinit var poster:ImageView
    lateinit var titleTv:TextView
    lateinit var descTv:TextView
    lateinit var rDate:TextView
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie)

        // Linking View Model
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        // Getting Id from Main Activity
        val myMovieId = intent.getIntExtra("EXTRA_TPMovieId",0)

        // Linking binding to views.
        poster= binding.moviePoster
        titleTv= binding.movieTitle
        descTv= binding.DescText
        rDate = binding.rDateTV

        // Getting Movie Details
        viewModel.getMovieDetails(myMovieId)

        // Setting Observers
        viewModel.myMovie.observe(this, Observer { mymovie->

            if(mymovie == null){
                val toast = Toast.makeText(this,"Network Error Getting Movie",
                    Toast.LENGTH_SHORT).show()

            }else{
                var pictureUrl = "https://www.themoviedb.org/t/p/original"+mymovie.posterPath.toString()
                Picasso.with(this)
                    .load(pictureUrl)
                    .into(poster)

                titleTv.text = mymovie.title
                descTv.text = mymovie.overview
                rDate.text = mymovie.releaseDate
            }
            return@Observer

        })

    }

    }


