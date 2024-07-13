package com.example.myapplication.features.detailsScreen.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.detailsScreen.domain.data.model.MovieDetailsResponse
import com.example.myapplication.features.detailsScreen.domain.data.repository.MovieDetailsRepository
import com.example.myapplication.sharedComponents.api.API
import kotlinx.coroutines.launch


class DetailsScreenViewModel(
    private val movieDetailsRepository: MovieDetailsRepository
) : ViewModel() {

    //region Properties
    private val _movieDetails = MutableLiveData<MovieDetailsResponse>()
    val movieDetails: LiveData<MovieDetailsResponse> get() = _movieDetails
    //endregion

    //region Methods
    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val response = movieDetailsRepository.getMovieDetails(movieId)
            if (response.isSuccess) {
                response.onSuccess {
                    _movieDetails.postValue(it)
                }.onFailure {
                    Log.e("FAILED.", "FAILED.")
                }
            }
        }
    }
}



class MovieDetailModule {
    companion object {
        val watchRepository: MovieDetailsRepository by lazy {
            MovieDetailsRepository(API.DetailsScreenService)
        }
    }
}


class DetailsScreenViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsScreenViewModel::class.java)) {
            return DetailsScreenViewModel(MovieDetailModule.watchRepository) as T
        }
        throw java.lang.IllegalArgumentException("wrong dependency")
    }
}