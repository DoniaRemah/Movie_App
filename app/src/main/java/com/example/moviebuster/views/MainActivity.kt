package com.example.moviebuster.views

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviebuster.R
import com.example.moviebuster.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tpAdapter = TopAndPopularAdapter()
    private val popularAdapter = TopAndPopularAdapter()
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

        if (!checkForInternet(this)) {
            val toast = Toast.makeText(this,"Check Your Internet Connection",Toast.LENGTH_SHORT).show()
        }else {
            // Getting Data from Server.
            viewModel.getTopRatedMovies()
            viewModel.getPopularMovies()
            viewModel.getUpcomingMovies()
            }



        // Setting Observers
        viewModel.topRatedList.observe(this,Observer{ toplist->

            if(toplist == null){
                val toast = Toast.makeText(this,"Network Error Getting Top Rated Movies List",
                    Toast.LENGTH_SHORT).show()

            }else{
                tpAdapter.submitList(toplist)
            }
            return@Observer
        })

        viewModel.popList.observe(this,Observer{ poplist->

            if(poplist == null){
                val toast = Toast.makeText(this@MainActivity,"Network Error Getting Popular Movies List",
                    Toast.LENGTH_SHORT).show()
            }else{
                popularAdapter.submitList(poplist)
            }
            return@Observer
        })

        viewModel.upList.observe(this,Observer{ uplist->

            if(uplist == null){
                val toast = Toast.makeText(this@MainActivity,"Network Error Getting Upcoming Movies List",
                    Toast.LENGTH_SHORT).show()
            }else{
                upcomingAdapter.submitList(uplist)
            }
            return@Observer
        })

        // Switching Activities
        upcomingAdapter.onMovieClickListener = object : UpcomingAdapter.OnMovieClickListener {
            override fun onMovieClicked(movieId: Int) {
                val intent = Intent(this@MainActivity, MovieActivity::class.java)
                intent.also {

                    it.putExtra("EXTRA_TPMovieId", movieId)
                    startActivity(intent)
                }
            }
        }


        popularAdapter.onMovieClickListener = object : TopAndPopularAdapter.OnMovieClickListener {
            override fun onMovieClicked(movieId: Int) {
                val intent = Intent(this@MainActivity, MovieActivity::class.java)
                intent.also {

                    it.putExtra("EXTRA_TPMovieId", movieId)
                    startActivity(intent)
                }
            }
        }


        tpAdapter.onMovieClickListener = object : TopAndPopularAdapter.OnMovieClickListener {
            override fun onMovieClicked(movieId: Int) {
                val intent = Intent(this@MainActivity, MovieActivity::class.java)
                intent.also {

                    it.putExtra("EXTRA_TPMovieId", movieId)
                    startActivity(intent)
                }
            }
        }

    }

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}


