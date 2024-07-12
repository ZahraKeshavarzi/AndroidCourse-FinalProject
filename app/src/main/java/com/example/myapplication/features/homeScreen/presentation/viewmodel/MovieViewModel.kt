package com.example.myapplication.features.homeScreen.presentation.viewmodel

import MovieResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.homeScreen.domain.data.repository.MovieRepository
import com.example.myapplication.sharedComponents.api.API
import kotlinx.coroutines.launch

//class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
//
//    //region Properties
//    private val _movies = MutableLiveData<List<MovieResponse.MovieItem>>()
//    val movies: LiveData<List<MovieResponse.MovieItem>> get() = _movies
//    private val _loadingState = MutableLiveData<Boolean>()
//    val loadingState: LiveData<Boolean> get() = _loadingState
//    //endregion
//
//    //region Methods
//    fun getAllMovies() {
//        viewModelScope.launch {
//            _loadingState.postValue(true)
//            val response = repository.getMoviesByGenre(14)
//            if (response.isSuccess) {
//                response.onSuccess {
//                    _movies.postValue(it)
//                    _loadingState.postValue(false)
//                }.onFailure {
//                    Log.e("hi", "hii")
//                }
//            }
//        }
//    }
//    //endregion
//
//}


class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieResponse.MovieItem>>()
    val movies: LiveData<List<MovieResponse.MovieItem>> get() = _movies

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> get() = _loadingState

    fun getAllMovies(genreId: Int) {
        viewModelScope.launch {
            _loadingState.postValue(true)
            try {
                val response = repository.getMoviesByGenre(genreId)
                _movies.postValue(response)
                _loadingState.postValue(false)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching movies", e)
                _loadingState.postValue(false)
            }
        }
    }
}


class MovieModule {
    companion object {
        val watchRepository: MovieRepository by lazy {
            MovieRepository(API.baseUserService)
        }
    }
}

class MovieViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(MovieModule.watchRepository) as T
        }
        throw java.lang.IllegalArgumentException("wrong dependency")
    }
}
