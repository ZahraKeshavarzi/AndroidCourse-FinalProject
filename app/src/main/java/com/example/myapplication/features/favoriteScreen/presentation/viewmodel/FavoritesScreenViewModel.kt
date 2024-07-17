//package com.example.myapplication.features.favoriteScreen.presentation.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewModelScope
//import com.example.myapplication.features.favoriteScreen.domain.data.repository.FavoritesRepository
//import com.example.myapplication.sharedComponents.MovieAppModule
//import com.example.myapplication.sharedComponents.MovieAppModule.Companion.database
//import com.example.myapplication.sharedComponents.db.FavoriteMoviesEntity
//import kotlinx.coroutines.launch
//
//class FavoritesScreenViewModel (private val repository: FavoritesRepository) : ViewModel() {
//
//    private val _favoriteMovies = MutableLiveData<List<FavoriteMoviesEntity>>()
//    val favoriteMovies: LiveData<List<FavoriteMoviesEntity>> get() = _favoriteMovies
//
//    private val _isEmptyFavorites = MutableLiveData<Boolean>()
//    val isEmptyFavorites: LiveData<Boolean> get() = _isEmptyFavorites
//
//    fun getFavoriteMovies() {
//        viewModelScope.launch {
//            val favoriteMovies = repository.getFavoriteMovies()
//            if (favoriteMovies.isNotEmpty()) {
//                _favoriteMovies.postValue(favoriteMovies)
//                _isEmptyFavorites.postValue(false)
//            } else {
//                _isEmptyFavorites.postValue(true)
//            }
//        }
//    }
//}
//
//class FavoriteModule {
//    companion object {
//        val watchRepository: FavoritesRepository by lazy {
//            FavoritesRepository(MovieAppModule.movieDao)
//        }
//    }
//}
//
//class FavoritesScreenViewModelFactory : ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(FavoritesScreenViewModel::class.java)) {
//            return FavoritesScreenViewModel(FavoriteModule.watchRepository) as T
//        }
//        throw java.lang.IllegalArgumentException("wrong dependency")
//    }
//}