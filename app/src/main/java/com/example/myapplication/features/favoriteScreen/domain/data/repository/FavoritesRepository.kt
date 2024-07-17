//package com.example.myapplication.features.favoriteScreen.domain.data.repository
//
//import com.example.myapplication.sharedComponents.db.FavoriteMoviesDao
//import com.example.myapplication.sharedComponents.db.FavoriteMoviesEntity
//
//class FavoritesRepository(private val favoriteMoviesDao: FavoriteMoviesDao) {
//
//    fun getFavoriteMovies(): MutableList<FavoriteMoviesEntity> {
//        val favoriteEntities = favoriteMoviesDao.getAllFavorites()
//        return favoriteEntities.map { entity ->
//            FavoriteMoviesEntity(
//                id = entity.id,
//                title = entity.title,
//                year = entity.year,
//                country = entity.country,
//                imdbRating = entity.imdbRating,
//                poster = entity.poster,
//                genres = entity.genres,
//                images = entity.images,
//                timestamp = entity.timestamp
//            )
//        }.toMutableList()
//    }
//}
//
////class FavoritesRepository(private val favoriteMoviesDao: FavoriteMoviesDao) {
////
////    fun getFavoriteMovies(): MutableList<MovieData> {
////        return favoriteMoviesDao.getAllFavorites()
////    }
////
////}