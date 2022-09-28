package com.example.moviebuster.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebuster.model.GeneralMovieDetails
import com.example.moviebuster.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel:ViewModel() {

    private val repo = Repository()

    private var _myMovie= MutableLiveData<GeneralMovieDetails>()
    val myMovie: LiveData<GeneralMovieDetails> = _myMovie


    fun getMovieDetails(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val res =  repo.getMovieDetails(id)
            withContext(Dispatchers.Main){
                if(res.isSucessful){
                    _myMovie.value= res.body
                }else{
                    _myMovie.value=res.body
                }
            }
        }
    }

}