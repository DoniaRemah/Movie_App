package com.example.moviebuster.views

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviebuster.model.TPList
import com.example.moviebuster.model.TPModel
import com.example.moviebuster.model.UpcomingList
import com.example.moviebuster.model.UpcomingModel
import com.example.moviebuster.network.RetrofitModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {

    private var service = RetrofitModule.getInstance().service

    var topRatedList = MutableLiveData<List<TPList>>()
    var popList = MutableLiveData<List<TPList>>()
    var upList = MutableLiveData<List<UpcomingList>>()


     fun getAllData() {
        service.getTopRatedMovies("fc47660226072874be57974ff797a0cd").enqueue(object :
            Callback<TPModel> {
            override fun onResponse(call: Call<TPModel>, response: Response<TPModel>) {
                topRatedList.value = response.body()?.topRatedList
            }

            override fun onFailure(call: Call<TPModel>, t: Throwable) {
                Log.e("Error", t.message!!)
            }
        })

        service.getPopularMovies("fc47660226072874be57974ff797a0cd").enqueue(object :
            Callback<TPModel> {
            override fun onResponse(call: Call<TPModel>, response: Response<TPModel>) {
                popList.value = response.body()?.topRatedList
            }

            override fun onFailure(call: Call<TPModel>, t: Throwable) {
                Log.e("Error", t.message!!)
            }
        })

        service.getUpcomingMovies("fc47660226072874be57974ff797a0cd").enqueue(object :
            Callback<UpcomingModel> {
            override fun onResponse(call: Call<UpcomingModel>, response: Response<UpcomingModel>) {
                upList.value = response.body()?.UpcomingList
            }

            override fun onFailure(call: Call<UpcomingModel>, t: Throwable) {
                Log.e("Error", t.message!!)
            }
        })
    }
}
