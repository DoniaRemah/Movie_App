package com.example.moviebuster.viewmodels


import android.app.Application
import androidx.lifecycle.*
import com.example.moviebuster.model.TopAndPopularList
import com.example.moviebuster.model.UpcomingList
import com.example.moviebuster.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(application: Application):AndroidViewModel(application){

    private var _topRatedList = MutableLiveData<List<TopAndPopularList>>()
    val topRatedList : LiveData<List<TopAndPopularList>>  get() = _topRatedList

    private var _popList = MutableLiveData<List<TopAndPopularList>>()
    val popList : LiveData<List<TopAndPopularList>>  get() = _popList

    private var _upList = MutableLiveData<List<UpcomingList>>()
    val upList : LiveData<List<UpcomingList>>  get() = _upList

    private val repo = Repository()


    fun getTopRatedMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val res =  repo.getTopRatedMovies()
            if(res.isSucessful){
                withContext(Dispatchers.Main) {
                    _topRatedList.value = res.body.topAndPopularList_model
                }

            }else{
                _topRatedList.postValue(null)
            }
        }
    }

    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val res =  repo.getPopularMovies()
            if(res.isSucessful){
                _popList.postValue(res.body.topAndPopularList_model)
            }else{
                _popList.postValue(null)
            }
        }
    }

    fun getUpcomingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val res =  repo.getUpcomingMovies()
            if(res.isSucessful){
                _upList.postValue(res.body.UpcomingList)
            }else{
                _upList.postValue(null)
            }
        }
    }



}
