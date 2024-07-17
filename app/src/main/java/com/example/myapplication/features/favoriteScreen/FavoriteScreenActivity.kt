//package com.example.myapplication.features.favoriteScreen
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.myapplication.databinding.ActivityFavoritescreenBinding
//import com.example.myapplication.features.favoriteScreen.presentation.ui.FavoritesAdapter
//import com.example.myapplication.features.favoriteScreen.presentation.viewmodel.FavoritesScreenViewModel
//import com.example.myapplication.features.favoriteScreen.presentation.viewmodel.FavoritesScreenViewModelFactory
//import com.example.myapplication.features.homeScreen.HomeScreenActivity
//import com.example.myapplication.features.homeScreen.presentation.ui.adapters.MovieAdapter
//import com.example.myapplication.features.searchScreen.SearchScreenActivity
//import com.example.myapplication.utils.extensions.gone
//
//class FavoriteScreenActivity : AppCompatActivity() {
//
//
//    //region Properties
//    private lateinit var binding: ActivityFavoritescreenBinding
//    private lateinit var favoritesAdapter: FavoritesAdapter
//    private lateinit var viewModel: FavoritesScreenViewModel
//    //endregion
//
//    //region lifecycle
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        initialBinding()
//        initialViewModel()
//        configViewModel()
//        getFavorites()
//    }
//    //endregion
//
//    //region methods
//    private fun initialBinding() {
//        binding = ActivityFavoritescreenBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }
//
//    private fun initialViewModel() {
//        viewModel = ViewModelProvider(this, FavoritesScreenViewModelFactory())[FavoritesScreenViewModel::class.java]
//
//    }
//
//    private fun configViewModel() {
//
//        viewModel.favoriteMovies.observe(this) { favoriteMovies ->
//
//            val movieAdapter = MovieAdapter(favoriteMovies)
//            binding.favoriteMoviesRecycler.adapter = movieAdapter
//            binding.favoriteMoviesRecycler.layoutManager =
//                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        }
//
//        viewModel.isEmptyFavorites.observe(this) {
//
//            if (it) {
//                binding.emptyIconImage.visibility = View.VISIBLE
//                binding.favoriteMoviesRecycler.visibility = View.GONE
//            } else {
//                binding.favoriteMoviesRecycler.visibility = View.VISIBLE
//                binding.emptyIconImage.visibility = View.GONE
//            }
//
//        }
//
//        binding.homeIcon.setOnClickListener {
//            val intent = Intent(this, HomeScreenActivity::class.java)
//            startActivity(intent)
//        }
//        binding.searchIcon.setOnClickListener {
//            val intent = Intent(this, SearchScreenActivity::class.java)
//            startActivity(intent)
//        }
//
//    }
//
//    private fun getFavorites() {
//        viewModel.getFavoriteMovies()
//    }
//}