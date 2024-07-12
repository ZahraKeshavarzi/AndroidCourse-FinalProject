package com.example.myapplication


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.features.homeScreen.presentation.ui.adapters.GenreAdapter
import com.example.myapplication.features.homeScreen.presentation.ui.adapters.MovieAdapter
import com.example.myapplication.features.homeScreen.presentation.viewmodel.HomeViewModel
import com.example.myapplication.features.homeScreen.presentation.viewmodel.MovieViewModelFactory


class MainActivity : AppCompatActivity() {


    //region Properties
    private lateinit var homeBinding: ActivityMainBinding
    private lateinit var homeViewModel: HomeViewModel
    //endregion


    //region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        initialBinding()
        initialViewModel()
        configViewModel()
        getLists()

    }
    //endregion


    //region Methods
    @SuppressLint("SetTextI18n")
    private fun initialBinding() {


        homeBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)


//        homeBinding.estatesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (dy != 0) {
//                    homeBinding.mapViewButton.visibility = View.GONE
//                }
//            }

//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    homeBinding.mapViewButton.visibility = View.VISIBLE
//                }
//            }
//        })
    }


    private fun initialViewModel() {
        homeViewModel = ViewModelProvider(this, MovieViewModelFactory())[HomeViewModel::class.java]
    }


    private fun configViewModel() {

        homeViewModel.movies.observe(this) { movies ->

            val movieAdapter = MovieAdapter(movies)
            homeBinding.moviesList.adapter = movieAdapter
            homeBinding.moviesList.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }

        homeViewModel.genres.observe(this) { genres ->

            val genreAdapter = GenreAdapter(genres)
            homeBinding.genresList.adapter = genreAdapter
            homeBinding.genresList.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }
    }


    private fun getLists() {
        homeViewModel.getAllMovies(genreId = 14)
        homeViewModel.getAllGenres()
    }
    //endregion
}