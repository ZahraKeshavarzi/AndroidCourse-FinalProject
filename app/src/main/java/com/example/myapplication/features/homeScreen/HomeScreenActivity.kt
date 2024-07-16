package com.example.myapplication.features.homeScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityHomescreenBinding
import com.example.myapplication.features.homeScreen.presentation.ui.adapters.GenreAdapter
import com.example.myapplication.features.homeScreen.presentation.ui.adapters.MovieAdapter
import com.example.myapplication.features.homeScreen.presentation.ui.adapters.PostersSliderAdapter
import com.example.myapplication.features.homeScreen.presentation.viewmodel.HomeScreenViewModel
import com.example.myapplication.features.homeScreen.presentation.viewmodel.HomeViewModelFactory
import com.example.myapplication.features.searchScreen.SearchScreenActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreenActivity : AppCompatActivity() {

    //region Properties
    private lateinit var homeScreenBinding: ActivityHomescreenBinding
    private lateinit var homeScreenViewModel: HomeScreenViewModel
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialBinding()
        initialViewModel()
        configViewModel()
        callAPI()
    }
    //endregion

    private fun initialBinding() {
        homeScreenBinding = ActivityHomescreenBinding.inflate(layoutInflater)
        setContentView(homeScreenBinding.root)
    }

    private fun initialViewModel() {
        homeScreenViewModel = ViewModelProvider(this, HomeViewModelFactory())[HomeScreenViewModel::class.java]
    }

    private fun configViewModel() {

        homeScreenViewModel.movies.observe(this) { movies ->

            val moviesList = movies.data
            val movieAdapter = MovieAdapter(moviesList)
            homeScreenBinding.moviesList.adapter = movieAdapter
            homeScreenBinding.moviesList.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

            val postersSliderAdapter = PostersSliderAdapter(moviesList)
            homeScreenBinding.postersSlider.adapter = postersSliderAdapter
            homeScreenBinding.postersSlider.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            homeScreenBinding.postersSlider.itemAnimator = DefaultItemAnimator()
        }


        homeScreenViewModel.genres.observe(this) { genres ->
            val genreAdapter = GenreAdapter(genres.data)
            homeScreenBinding.genresList.adapter = genreAdapter
            homeScreenBinding.genresList.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }


        HomeScreenViewModel.genreId.observe(this) { genreID ->
            homeScreenViewModel.getAllMovies(genreID)
        }


        homeScreenBinding.searchIcon.setOnClickListener {
            val intent = Intent(this, SearchScreenActivity::class.java)
            startActivity(intent)
        }
    }


    private fun callAPI() {
        CoroutineScope(Dispatchers.Main).launch {
            homeScreenViewModel.getAllMovies(genreId = 14)
            homeScreenViewModel.getAllGenres()
        }
    }
}
