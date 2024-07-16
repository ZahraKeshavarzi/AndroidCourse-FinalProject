//package com.example.myapplication.features.favoriteScreen.presentation.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.myapplication.features.homeScreen.domain.data.model.MovieData
//import ir.aryasaadat.movieapp.shared_component.db.MovieEntity
//import ir.aryasaadat.movieapp.shared_component.repository.FavoriteMovieRepository
//import ir.aryasaadat.movieapp.application.MovieAppModule
//
//
//class FavoriteViewModel(
//    private val repository: FavoriteMovieRepository
//) :
//    ViewModel() {
//
//    private val _favoriteMovies = MutableLiveData<MutableList<MovieData>>()
//    val favoriteMovies: LiveData<MutableList<MovieData>> get() = _favoriteMovies
//    private val _isEmptyList = MutableLiveData<Boolean>()
//    val isEmptyList: LiveData<Boolean> get() = _isEmptyList
//
//    fun loadFavoriteMovies() {
//        val list = repository.getAllFavoriteMovies()
//        if (list.isNotEmpty()) {
//            _favoriteMovies.postValue(list)
//            _isEmptyList.postValue(false)
//        } else {
//            _isEmptyList.postValue(true)
//        }
//    }
//}
//
//class FavoriteModule {
//    companion object {
//        val repository: FavoriteMovieRepository by lazy { FavoriteMovieRepository(MovieAppModule.movieDao) }
//    }
//}
//
//class FavoriteViewModelFactory :
//    ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
//            return FavoriteViewModel(FavoriteModule.repository) as T
//        }
//        throw java.lang.IllegalArgumentException("wrong dependency")
//    }
//}