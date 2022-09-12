package com.example.moviebuster.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.moviebuster.R
import com.example.moviebuster.model.*
import com.example.moviebuster.network.RetrofitModule
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Movie : AppCompatActivity() {

    lateinit var poster:ImageView
    lateinit var titleTv:TextView
    lateinit var descTv:TextView
    lateinit var rDate:TextView
//    val poster:ImageView = findViewById<ImageView>(R.id.moviePoster)
//    val titleTv:TextView = findViewById<TextView>(R.id.movieTitle)
//    val descTv:TextView = findViewById<TextView>(R.id.DescText)
//    val rDate:TextView = findViewById<TextView>(R.id.rDateTV)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)


        val mymovie = intent.getIntExtra("EXTRA_TPMovieId",0)

        poster= findViewById<ImageView>(R.id.moviePoster)
        titleTv= findViewById<TextView>(R.id.movieTitle)
        descTv= findViewById<TextView>(R.id.DescText)
        rDate = findViewById<TextView>(R.id.rDateTV)

        getTPMovie(mymovie)

    }

    var service = RetrofitModule.getInstance().service



   private fun getTPMovie(movieId:Int){
        service.getMovieDetails(movieId,"fc47660226072874be57974ff797a0cd").enqueue(object : Callback<MovieDetails> {

            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {


                titleTv.text = response.body()?.title.toString()
                descTv.text = response.body()?.overview.toString()
                rDate.text=response.body()?.releaseDate.toString()

                var pictureUrl = "https://www.themoviedb.org/t/p/w220_and_h330_face"+response.body()?.posterPath.toString()
                Picasso.with(this@Movie)
                    .load(pictureUrl)
                    .into(poster)

            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.e("Error", t.message!!)
            }

        })

    }
    }


