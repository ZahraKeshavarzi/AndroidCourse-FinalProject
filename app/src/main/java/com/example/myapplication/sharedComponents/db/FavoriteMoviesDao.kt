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
//    @Query("DELETE FROM ${Constants.FAVORITE_MOVIE_TABLE} WHERE name = :movieName")
//    fun deleteMovie(movieName: String)
//
//    @Query("SELECT COUNT(*) FROM favorites WHERE name = :name")
//    fun isFavorite(name: String): Int
//
//    @Query("SELECT * FROM ${Constants.FAVORITE_MOVIE_TABLE} ORDER BY timestamp DESC")
//    fun getAllFavorites(): MutableList<FavoriteMoviesEntity>
//
//
//}