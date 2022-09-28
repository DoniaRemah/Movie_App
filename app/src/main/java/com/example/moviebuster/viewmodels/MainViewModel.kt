package com.example.moviebuster.viewmodels


import android.app.Application
import androidx.lifecycle.*
import com.example.moviebuster.model.GeneralMovieDetails
import com.example.moviebuster.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(application: Application):AndroidViewModel(application){

    private var _topRatedList = MutableLiveData<List<GeneralMovieDetails>>()
    val topRatedList : LiveData<List<GeneralMovieDetails>>  get() = _topRatedList

    private var _popList = MutableLiveData<List<GeneralMovieDetails>>()
    val popList : LiveData<List<GeneralMovieDetails>>  get() = _popList

    private var _upList = MutableLiveData<List<GeneralMovieDetails>>()
    val upList : LiveData<List<GeneralMovieDetails>>  get() = _upList

    private val repo = Repository()


    fun getTopRatedMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val res =  repo.getTopRatedMovies()

            withContext(Dispatchers.Main){
                if(res.isSucessful){
                    _topRatedList.value = res.body.movieList

                }else{
                    _topRatedList.value= null
                }
            }

        }
    }

    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val res =  repo.getPopularMovies()

            withContext(Dispatchers.Main){
                if(res.isSucessful){
                    _popList.value=res.body.movieList
                }else{
                    _popList.value =null
                }
            }


        }
    }

    fun getUpcomingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val res =  repo.getUpcomingMovies()

            withContext(Dispatchers.Main){
                if(res.isSucessful){
                    _upList.value = res.body.movieList
                }else{
                    _upList.value = null
                }
            }

        }
    }



}
