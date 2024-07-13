package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.features.homeScreen.presentation.ui.adapters.GenreAdapter
import com.example.myapplication.features.homeScreen.presentation.ui.adapters.MovieAdapter
import com.example.myapplication.features.homeScreen.presentation.viewmodel.HomeViewModel
import com.example.myapplication.features.homeScreen.presentation.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var homeScreenBinding: ActivityMainBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialBinding()
        initialViewModel()
        configViewModel()
        getLists()
    }

    private fun initialBinding() {
        homeScreenBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(homeScreenBinding.root)
    }

    private fun initialViewModel() {
        homeViewModel = ViewModelProvider(this, MovieViewModelFactory())[HomeViewModel::class.java]
    }

    private fun configViewModel() {

        homeViewModel.movies.observe(this) { movies ->
            val movieAdapter = MovieAdapter(movies)
            homeScreenBinding.moviesList.adapter = movieAdapter
            homeScreenBinding.moviesList.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }

        homeViewModel.genres.observe(this) { genres ->
            val genreAdapter = GenreAdapter(genres)
            homeScreenBinding.genresList.adapter = genreAdapter
            homeScreenBinding.genresList.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getLists() {
        CoroutineScope(Dispatchers.Main).launch {
            homeViewModel.getAllMovies(genreId = 14)
            homeViewModel.getAllGenres()
        }
    }
}
