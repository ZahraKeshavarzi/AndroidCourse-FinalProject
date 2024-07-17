package com.example.myapplication.features.favoritesScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityFavoritescreenBinding
import com.example.myapplication.features.favoritesScreen.presentation.ui.adapters.FavoriteMoviesAdapter
import com.example.myapplication.features.favoritesScreen.presentation.viewmodel.FavoritesScreenViewModel
import com.example.myapplication.features.favoritesScreen.presentation.viewmodel.FavoritesScreenViewModelFactory
import com.example.myapplication.features.homeScreen.HomeScreenActivity
import com.example.myapplication.features.searchScreen.SearchScreenActivity

class FavoritesScreenActivity : AppCompatActivity() {

    //region Properties
    private lateinit var binding: ActivityFavoritescreenBinding
    private lateinit var viewModel: FavoritesScreenViewModel
    //endregion

    //region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialBinding()
        initialViewModel()
        configViewModel()
        getFavorites()
    }
    //endregion

    //region methods
    private fun initialBinding() {
        binding = ActivityFavoritescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initialViewModel() {
        viewModel = ViewModelProvider(this, FavoritesScreenViewModelFactory())[FavoritesScreenViewModel::class.java]
    }

    private fun configViewModel() {
        viewModel.movies.observe(this) { favoriteMovies ->
            val movieAdapter = FavoriteMoviesAdapter(favoriteMovies)
            binding.favoriteMoviesRecycler.adapter = movieAdapter
            binding.favoriteMoviesRecycler.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

            if (favoriteMovies.isEmpty()) {
                binding.emptyIconImage.visibility = View.VISIBLE
                binding.emptyText.visibility = View.VISIBLE
                binding.favoriteMoviesRecycler.visibility = View.GONE
            } else {
                binding.favoriteMoviesRecycler.visibility = View.VISIBLE
                binding.emptyIconImage.visibility = View.GONE
                binding.emptyText.visibility = View.GONE
            }
        }

        binding.homeIcon.setOnClickListener {
            val intent = Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
        }
        binding.searchIcon.setOnClickListener {
            val intent = Intent(this, SearchScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getFavorites() {
        viewModel.getFavoriteMovies()
    }
}
