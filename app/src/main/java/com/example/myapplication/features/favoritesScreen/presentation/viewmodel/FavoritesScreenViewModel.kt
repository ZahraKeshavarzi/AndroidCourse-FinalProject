package com.example.myapplication.features.favoritesScreen.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.features.detailsScreen.domain.data.model.MovieDetailsData
import com.example.myapplication.features.favoritesScreen.domain.data.repository.FavoriteMoviesRepository


class FavoritesScreenViewModel(private val favoriteMoviesRepository: FavoriteMoviesRepository) : ViewModel() {

    //region Properties
    private val _movies = MutableLiveData<List<MovieDetailsData>>()
    val movies: LiveData<List<MovieDetailsData>> get() = _movies
    //endregion

    fun getFavoriteMovies() {
        val favoriteMovies = favoriteMoviesRepository.getAllFavoriteMovies()
        _movies.value = favoriteMovies
    }

    fun isEmptyFavorites(): Boolean {
        return _movies.value.isNullOrEmpty()
    }
}



class MovieModule {
    companion object {
        val watchRepository: FavoriteMoviesRepository by lazy {
            FavoriteMoviesRepository()
        }
    }
}

class FavoritesScreenViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesScreenViewModel::class.java)) {
            return FavoritesScreenViewModel(MovieModule.watchRepository) as T
        }
        throw java.lang.IllegalArgumentException("wrong dependency")
    }
}
