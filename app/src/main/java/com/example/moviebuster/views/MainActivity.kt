package com.example.moviebuster.views

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviebuster.R
import com.example.moviebuster.adapters.TopAndPopularAdapter
import com.example.moviebuster.adapters.UpcomingAdapter
import com.example.moviebuster.databinding.ActivityMainBinding
import com.example.moviebuster.viewmodels.MainViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tpAdapter = TopAndPopularAdapter()
    private val popularAdapter = TopAndPopularAdapter()
    private val upcomingAdapter = UpcomingAdapter()

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        checkingInternetConnection()
        initializing()
        settingObservers()
        settingListeners()

    }


    private fun settingListeners(){
        tpAdapter.onMovieClickListener = object : TopAndPopularAdapter.OnMovieClickListener {
            override fun onMovieClicked(movieId: Int) {
                switchActivity(movieId)
            }
        }

        popularAdapter.onMovieClickListener = object : TopAndPopularAdapter.OnMovieClickListener {
            override fun onMovieClicked(movieId: Int) {
                switchActivity(movieId)
            }
        }

        upcomingAdapter.onMovieClickListener = object : UpcomingAdapter.OnMovieClickListener {
            override fun onMovieClicked(movieId: Int) {
                switchActivity(movieId)
            }
        }

    }

    private fun switchActivity(movieId:Int){
        val intent = Intent(this@MainActivity, MovieActivity::class.java)
        intent.also {

            it.putExtra("EXTRA_TPMovieId", movieId)
            startActivity(intent)
        }
    }



    private fun initializing(){

        binding.apply {
            rvPop.adapter = popularAdapter
            rvTopRated.adapter = tpAdapter
            rvUpcoming.adapter = upcomingAdapter
        }

    }


    private fun checkingInternetConnection(){
        if (!checkForInternet(this)) {
            Toast.makeText(this,"Check Your Internet Connection",Toast.LENGTH_SHORT).show()
        }else {
            // Getting Data from Server.
            viewModel.getTopRatedMovies()
            viewModel.getPopularMovies()
            viewModel.getUpcomingMovies()
        }
    }



    private fun settingObservers(){

        viewModel.topRatedList.observe(this) { toplist ->

            if (toplist == null) {
                Toast.makeText(
                    this, "Network Error Getting Top Rated Movies List",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                lifecycleScope.launch {
                    tpAdapter.submitList(toplist)
                }
            }
        }

        viewModel.popList.observe(this) { poplist ->

            if (poplist == null) {
                Toast.makeText(
                    this, "Network Error Getting Popular Movies List",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                lifecycleScope.launch {
                    popularAdapter.submitList(poplist)
                }
            }
        }

        viewModel.upList.observe(this) { uplist ->

            if (uplist == null) {
                Toast.makeText(
                    this@MainActivity, "Network Error Getting Upcoming Movies List",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                lifecycleScope.launch {
                    upcomingAdapter.submitList(uplist)
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

        // Returns a Network object corresponding to
        // the currently active default data network.
        val network = connectivityManager.activeNetwork ?: return false

     //Representation of the capabilities of an active network.
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
    }
}


