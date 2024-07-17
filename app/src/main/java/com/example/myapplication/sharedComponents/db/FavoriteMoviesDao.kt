//package com.example.myapplication.sharedComponents.db
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//
//@Dao
//interface FavoriteMoviesDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertMovie(movie: FavoriteMoviesEntity)
//
//    @Query("DELETE FROM ${Constants.FAVORITE_MOVIE_TABLE} WHERE title = :name")
//    fun deleteMovie(name: String)
//
//    @Query("SELECT COUNT(*) FROM ${Constants.FAVORITE_MOVIE_TABLE} WHERE title = :name")
//    fun isFavorite(name: String): Int
//
//    @Query("SELECT * FROM ${Constants.FAVORITE_MOVIE_TABLE} ORDER BY timestamp DESC")
//    fun getAllFavorites(): MutableList<FavoriteMoviesEntity>
//
//
//}