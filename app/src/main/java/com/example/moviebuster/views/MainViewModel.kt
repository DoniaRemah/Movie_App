package com.example.moviebuster.views


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebuster.model.TopAndPopularList
import com.example.moviebuster.model.UpcomingList
import com.example.moviebuster.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel:ViewModel() {

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
            if(res?.isSucessful == true){
                _topRatedList.postValue(res!!.body.topAndPopularList_model)
            }else{
                _topRatedList.postValue(null)
            }
        }
    }


    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val res =  repo.getPopularMovies()
            if(res?.isSucessful == true){
                _popList.postValue(res.body.topAndPopularList_model)
            }else{
                _popList.postValue(null)
            }
        }
    }

    fun getUpcomingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val res =  repo.getUpcomingMovies()
            if(res?.isSucessful == true){
                _upList.postValue(res.body.UpcomingList)
            }else{
                _upList.postValue(null)
            }
        }
    }



}
