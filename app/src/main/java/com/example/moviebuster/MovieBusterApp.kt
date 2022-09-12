package com.example.moviebuster

import android.app.Application
import android.widget.TextView
import com.example.moviebuster.network.RetrofitModule


class MovieBusterApp: Application(){

    override fun onCreate() {
        super.onCreate()
        RetrofitModule.intialize(this)



    }
}