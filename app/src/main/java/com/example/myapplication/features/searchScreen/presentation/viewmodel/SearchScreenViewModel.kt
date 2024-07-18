package com.example.myapplication.features.searchScreen.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.homeScreen.domain.data.model.MovieDescription
import com.example.myapplication.features.homeScreen.domain.data.model.MovieMetadata
import com.example.myapplication.features.homeScreen.domain.data.model.MovieResponse
import com.example.myapplication.features.searchScreen.domain.data.repository.MovieSearchRepository
import com.example.myapplication.sharedComponents.api.API
import kotlinx.coroutines.launch


class SearchScreenViewModel(private val movieSearchRepository: MovieSearchRepository) : ViewModel() {

    //region Properties
    private val _movies = MutableLiveData<MovieResponse>()
    val movies: LiveData<MovieResponse> get() = _movies

    companion object{
        val searchKeyword = MutableLiveData("The")
    }
    //endregion

    //region Methods
    fun getAllMoviesBySearchKeyword(searchKeyword: String) {
        viewModelScope.launch {
            val response = movieSearchRepository.getAllMoviesByKeyword(searchKeyword)
            if (response.isSuccess) {
                response.onSuccess {
                    _movies.postValue(it)
                }.onFailure {
                }
            }
        }
    }


    fun clearMovies() {
        val emptyResponse = MovieResponse(
            status = 0,
            description = MovieDescription(en="", fa=""),
            data = emptyList(),
            metadata = MovieMetadata(
                hasNext = false,
                hasPrev = false,
                currentPage = 0,
                perPage = 0,
                pageCount = 0,
                totalCount = 0
            )
        )
        _movies.postValue(emptyResponse)
    }
}


class MovieModule {
    companion object {
        val watchRepository: MovieSearchRepository by lazy {
            MovieSearchRepository(API.searchScreenService)
        }
    }
}


class SearchScreenViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchScreenViewModel::class.java)) {
            return SearchScreenViewModel(MovieModule.watchRepository) as T
        }
        throw java.lang.IllegalArgumentException("wrong dependency")
    }
}
