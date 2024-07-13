package com.example.myapplication.features.homeScreen.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.homeScreen.domain.data.model.Genre
import com.example.myapplication.features.homeScreen.domain.data.model.MovieResponse
import com.example.myapplication.features.homeScreen.domain.data.repository.GenreRepository
import com.example.myapplication.features.homeScreen.domain.data.repository.MovieRepository
import com.example.myapplication.sharedComponents.api.API
import kotlinx.coroutines.launch


class HomeViewModel(private val movieRepository: MovieRepository, private val genreRepository: GenreRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieResponse.MovieItem>>()
    val movies: LiveData<List<MovieResponse.MovieItem>> get() = _movies

//    private val _loadingState = MutableLiveData<Boolean>()
//    val loadingState: LiveData<Boolean> get() = _loadingState

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres
    fun getAllMovies(genreId: Int) {
        viewModelScope.launch {
            //_loadingState.postValue(true)
            try {
                val response = movieRepository.getMoviesByGenre(genreId)
                _movies.postValue(response)
                //_loadingState.postValue(false)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching movies", e)
                //_loadingState.postValue(false)
            }
        }
    }

    fun getAllGenres() {
        val genreList = genreRepository.getAllGenres()
        _genres.postValue(genreList)
    }
}


class MovieModule {
    companion object {
        val watchRepository: MovieRepository by lazy {
            MovieRepository(API.homeScreenService)
        }
    }
}


class GenreModule {
    companion object {
        val watchRepository: GenreRepository by lazy {
            GenreRepository()
        }
    }
}

class MovieViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(MovieModule.watchRepository, GenreModule.watchRepository) as T
        }
        throw java.lang.IllegalArgumentException("wrong dependency")
    }
}
