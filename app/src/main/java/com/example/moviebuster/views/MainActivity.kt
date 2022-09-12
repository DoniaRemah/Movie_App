package com.example.moviebuster.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuster.R
import com.example.moviebuster.model.*
import com.example.moviebuster.network.RetrofitModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val tpAdapter = TPAdapter()
    private val popularAdapter = TPAdapter()
    private val upcomingAdapter = UpcomingAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<RecyclerView>(R.id.rvPop).adapter = popularAdapter
        findViewById<RecyclerView>(R.id.rvTopRated).adapter = tpAdapter
        findViewById<RecyclerView>(R.id.rvUpcoming).adapter = upcomingAdapter

        upcomingAdapter.onMovieClickListener  = object : UpcomingAdapter.OnMovieClickListener{
            override fun onMovieClicked(movieId:Int) {
                val intent= Intent(this@MainActivity,Movie::class.java)
                intent.also {

                    it.putExtra("EXTRA_TPMovieId",movieId)
                    startActivity(intent)
                }
            }
        }


        popularAdapter.onMovieClickListener  = object : TPAdapter.OnMovieClickListener {
            override fun onMovieClicked(movieId:Int) {
                val intent= Intent(this@MainActivity,Movie::class.java)
                intent.also {

                    it.putExtra("EXTRA_TPMovieId",movieId)
                    startActivity(intent)
                }
            }
        }


        tpAdapter.onMovieClickListener  = object : TPAdapter.OnMovieClickListener {
            override fun onMovieClicked(movieId:Int) {
                val intent= Intent(this@MainActivity,Movie::class.java)
                intent.also {

                    it.putExtra("EXTRA_TPMovieId",movieId)
                    startActivity(intent)
                    }
                }
            }


        getAllData()
        }


    var service = RetrofitModule.getInstance().service


    private fun getAllData() {
        service.getTopRatedMovies("fc47660226072874be57974ff797a0cd").enqueue(object : Callback<TPModel> {
            override fun onResponse(call: Call<TPModel>, response: Response<TPModel>) {
                tpAdapter.submitList(response.body()?.topRatedList)
            }

            override fun onFailure(call: Call<TPModel>, t: Throwable) {
                Log.e("Error", t.message!!)
            }
        })

        service.getPopularMovies("fc47660226072874be57974ff797a0cd").enqueue(object : Callback<TPModel> {
            override fun onResponse(call: Call<TPModel>, response: Response<TPModel>) {
                popularAdapter.submitList(response.body()?.topRatedList)
            }

            override fun onFailure(call: Call<TPModel>, t: Throwable) {
                Log.e("Error", t.message!!)
            }
        })

        service.getUpcomingMovies("fc47660226072874be57974ff797a0cd").enqueue(object : Callback<UpcomingModel> {
            override fun onResponse(call: Call<UpcomingModel>, response: Response<UpcomingModel>) {
                upcomingAdapter.submitList(response.body()?.UpcomingList)
            }

            override fun onFailure(call: Call<UpcomingModel>, t: Throwable) {
                Log.e("Error", t.message!!)
            }
        })
    }
}