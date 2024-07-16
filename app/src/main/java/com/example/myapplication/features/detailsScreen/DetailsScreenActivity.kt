package com.example.myapplication.features.detailsScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityDetailsscreenBinding
import com.example.myapplication.features.detailsScreen.presentation.ui.adapters.MovieImageAdapter
import com.example.myapplication.features.detailsScreen.presentation.viewmodel.DetailsScreenViewModel
import com.example.myapplication.features.detailsScreen.presentation.viewmodel.DetailsScreenViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsScreenActivity : AppCompatActivity() {

    //region Properties
    private lateinit var detailsScreenBinding: ActivityDetailsscreenBinding
    private lateinit var detailsScreenViewModel: DetailsScreenViewModel
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

    //region Methods
    private fun initialBinding() {
        detailsScreenBinding = ActivityDetailsscreenBinding.inflate(layoutInflater)
        setContentView(detailsScreenBinding.root)
    }

    private fun initialViewModel() {
        detailsScreenViewModel = ViewModelProvider(this, DetailsScreenViewModelFactory())[DetailsScreenViewModel::class.java]
    }

    private fun configViewModel() {
        detailsScreenViewModel.movieDetails.observe(this) { movieDetail ->

            val movieImageAdapter = MovieImageAdapter(movieDetail.data.images)

            detailsScreenBinding.backIcon.setOnClickListener {
                finish()
            }

            Glide.with((detailsScreenBinding.moviePoster.context))
                .load(movieDetail.data.poster)
                .into(detailsScreenBinding.moviePoster)

            Glide.with(detailsScreenBinding.backgroundImage.context)
                .load(movieDetail.data.poster)
                .into(detailsScreenBinding.backgroundImage)

            detailsScreenBinding.movieTitle.text = movieDetail.data.title
            detailsScreenBinding.ratingText.text = movieDetail.data.imdbRating
            detailsScreenBinding.durationText.text = movieDetail.data.runtime
            detailsScreenBinding.dateText.text = movieDetail.data.released
            detailsScreenBinding.summaryText.text = movieDetail.data.plot
            detailsScreenBinding.actorsText.text = movieDetail.data.actors

            detailsScreenBinding.recyclerViewImages.adapter = movieImageAdapter
            detailsScreenBinding.recyclerViewImages.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            detailsScreenBinding.recyclerViewImages.itemAnimator = DefaultItemAnimator()
        }
    }

    private fun callAPI() {
        CoroutineScope(Dispatchers.Main).launch {
            val movieId = intent.getIntExtra("movieId", 1)
            detailsScreenViewModel.getMovieDetails((movieId))
        }
    }
    //endregion
}
