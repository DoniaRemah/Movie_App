package com.example.moviebuster.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebuster.model.MovieDetails
import com.example.moviebuster.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel:ViewModel() {

    private val repo = Repository()

    private var _myMovie= MutableLiveData<MovieDetails>()
    val myMovie: LiveData<MovieDetails> = _myMovie


    fun getMovieDetails(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val res =  repo.getMovieDetails(id)
            if(res.isSucessful){
                _myMovie.postValue(res.body)
            }else{
                _myMovie.postValue(null)
            }
        }
    }

}