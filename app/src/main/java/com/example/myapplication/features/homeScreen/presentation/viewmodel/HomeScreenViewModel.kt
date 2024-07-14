package com.example.myapplication.features.homeScreen.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.homeScreen.domain.data.model.GenreResponse
import com.example.myapplication.features.homeScreen.domain.data.model.MovieResponse
import com.example.myapplication.features.homeScreen.domain.data.repository.GenreRepository
import com.example.myapplication.features.homeScreen.domain.data.repository.MovieRepository
import com.example.myapplication.sharedComponents.api.API
import kotlinx.coroutines.launch


class HomeViewModel(private val movieRepository: MovieRepository, private val genreRepository: GenreRepository) : ViewModel() {

    //region Properties
    private val _movies = MutableLiveData<MovieResponse>()
    val movies: LiveData<MovieResponse> get() = _movies


    private val _genres = MutableLiveData<GenreResponse>()
    val genres: LiveData<GenreResponse> get() = _genres

    companion object{
        val genreId = MutableLiveData(14)
    }
    //endregion

    //region Methods
    fun getAllMovies(genreId: Int) {
        viewModelScope.launch {
            val response = movieRepository.getAllMovies(genreId)
            if (response.isSuccess) {
                response.onSuccess {
                    _movies.postValue(it)
                }.onFailure {
                }
            }
        }
    }

    fun getAllGenres(){
        viewModelScope.launch {
            val response = genreRepository.getAllGenres()
            if (response.isSuccess) {
                response.onSuccess {
                    _genres.postValue(it)
                }.onFailure {
                    //TODO
                }
            }
        }
    }
}


class MovieModule {
    companion object {
        val watchRepository: MovieRepository by lazy {
            MovieRepository(API.homeScreenServiceMovies)
        }
    }
}


class GenreModule {
    companion object {
        val watchRepository: GenreRepository by lazy {
            GenreRepository(API.homeScreenServiceGenres)
        }
    }
}

class HomeViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(MovieModule.watchRepository, GenreModule.watchRepository) as T
        }
        throw java.lang.IllegalArgumentException("wrong dependency")
    }
}
