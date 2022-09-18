package com.example.moviebuster.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviebuster.R
import com.example.moviebuster.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val tpAdapter = TPAdapter()
    private val popularAdapter = TPAdapter()
    private val upcomingAdapter = UpcomingAdapter()

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //View Model
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Initializing
        binding.rvPop.adapter = popularAdapter
        binding.rvTopRated.adapter = tpAdapter
        binding.rvUpcoming.adapter = upcomingAdapter


        // Setting Observers
        viewModel.topRatedList.observe(this,Observer{ toplist->
            tpAdapter.submitList(toplist)
        })

        viewModel.popList.observe(this,Observer{ poplist->
            popularAdapter.submitList(poplist)
        })

        viewModel.upList.observe(this,Observer{ uplist->
            upcomingAdapter.submitList(uplist)
        })

        // Getting Movies
        viewModel.getAllData()

        // Switching Activities
        upcomingAdapter.onMovieClickListener = object : UpcomingAdapter.OnMovieClickListener {
            override fun onMovieClicked(movieId: Int) {
                val intent = Intent(this@MainActivity, Movie::class.java)
                intent.also {

                    it.putExtra("EXTRA_TPMovieId", movieId)
                    startActivity(intent)
                }
            }
        }


        popularAdapter.onMovieClickListener = object : TPAdapter.OnMovieClickListener {
            override fun onMovieClicked(movieId: Int) {
                val intent = Intent(this@MainActivity, Movie::class.java)
                intent.also {

                    it.putExtra("EXTRA_TPMovieId", movieId)
                    startActivity(intent)
                }
            }
        }


        tpAdapter.onMovieClickListener = object : TPAdapter.OnMovieClickListener {
            override fun onMovieClicked(movieId: Int) {
                val intent = Intent(this@MainActivity, Movie::class.java)
                intent.also {

                    it.putExtra("EXTRA_TPMovieId", movieId)
                    startActivity(intent)
                }
            }
        }

    }


}