package com.example.myapplication.features.homeScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityHomescreenBinding
import com.example.myapplication.features.favoritesScreen.FavoritesScreenActivity
import com.example.myapplication.features.homeScreen.domain.data.model.MovieData
import com.example.myapplication.features.homeScreen.presentation.ui.adapters.GenreAdapter
import com.example.myapplication.features.homeScreen.presentation.ui.adapters.MovieAdapter
import com.example.myapplication.features.homeScreen.presentation.ui.adapters.PostersSliderAdapter
import com.example.myapplication.features.homeScreen.presentation.viewmodel.HomeScreenViewModel
import com.example.myapplication.features.homeScreen.presentation.viewmodel.HomeViewModelFactory
import com.example.myapplication.features.searchScreen.SearchScreenActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreenActivity : AppCompatActivity(), PostersSliderAdapter.OnItemSelectedListener {

    private lateinit var postersSliderAdapter: PostersSliderAdapter
    private lateinit var indicatorViews: MutableList<ImageView>
    private lateinit var homeScreenBinding: ActivityHomescreenBinding
    private lateinit var homeScreenViewModel: HomeScreenViewModel


    private var moviesList: List<MovieData> = emptyList()
    private lateinit var layoutManager: LinearLayoutManager
    private val handler = Handler(Looper.getMainLooper())
    private var isDragging = false
    private var scrolledDistance = 0

    private val sliderRunnable = object : Runnable {
        override fun run() {
            if (!isDragging) {
                val nextItem = (layoutManager.findFirstVisibleItemPosition() + 1) % moviesList.size
                homeScreenBinding.postersSlider.smoothScrollToPosition(nextItem)
                highlightIndicator(nextItem)
            }
            handler.postDelayed(this, 4000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialBinding()
        initialViewModel()
        configViewModel()
        callAPI()
    }

    private fun initialBinding() {
        homeScreenBinding = ActivityHomescreenBinding.inflate(layoutInflater)
        setContentView(homeScreenBinding.root)
    }

    private fun initialViewModel() {
        homeScreenViewModel = ViewModelProvider(this, HomeViewModelFactory())[HomeScreenViewModel::class.java]
    }

    private fun configViewModel() {
        homeScreenViewModel.movies.observe(this) { movies ->
            moviesList = movies.data

            initRecyclerView()
            initIndicatorDots()
            startAutoSlide()
        }

        homeScreenViewModel.genres.observe(this) { genres ->
            val genreAdapter = GenreAdapter(genres.data)
            homeScreenBinding.genresList.adapter = genreAdapter
            homeScreenBinding.genresList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }

        HomeScreenViewModel.genreId.observe(this) { genreID ->
            homeScreenViewModel.getAllMovies(genreID)
        }
        
        homeScreenBinding.searchIcon.setOnClickListener {
            val intent = Intent(this, SearchScreenActivity::class.java)
            startActivity(intent)
        }

        homeScreenBinding.likeIcon.setOnClickListener {
            val intent = Intent(this, FavoritesScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        val movieAdapter = MovieAdapter(moviesList)
        homeScreenBinding.moviesList.adapter = movieAdapter
        homeScreenBinding.moviesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        postersSliderAdapter = PostersSliderAdapter(moviesList, this)
        homeScreenBinding.postersSlider.adapter = postersSliderAdapter
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        homeScreenBinding.postersSlider.layoutManager = layoutManager
        homeScreenBinding.postersSlider.itemAnimator = DefaultItemAnimator()

        homeScreenBinding.postersSlider.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    val visibleChild = layoutManager.findViewByPosition(firstVisibleItemPosition)
                    val halfWidth = recyclerView.width / 2

                    if (visibleChild != null) {
                        val childMidpoint = (visibleChild.left + visibleChild.right) / 2
                        val childWidth = visibleChild.width

                        if (Math.abs(childMidpoint - halfWidth) >= childWidth / 2) {
                            val nextItem = if (childMidpoint > halfWidth) {
                                firstVisibleItemPosition + 1
                            } else {
                                firstVisibleItemPosition
                            }
                            homeScreenBinding.postersSlider.smoothScrollToPosition(nextItem)
                            highlightIndicator(nextItem)
                        } else {
                            homeScreenBinding.postersSlider.smoothScrollToPosition(firstVisibleItemPosition)
                            highlightIndicator(firstVisibleItemPosition)
                        }
                    }
                    isDragging = false
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    isDragging = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                scrolledDistance += dx
            }
        })
    }

    private fun initIndicatorDots() {
        indicatorViews = mutableListOf()
        val indicatorContainer = findViewById<LinearLayout>(R.id.indicatorContainer)
        indicatorContainer.removeAllViews()

        for (i in moviesList.indices) {
            val dot = ImageView(this)
            dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_inactive))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            dot.layoutParams = params
            indicatorContainer.addView(dot)
            indicatorViews.add(dot)
        }

        highlightIndicator(0)
    }

    private fun callAPI() {
        CoroutineScope(Dispatchers.Main).launch {
            homeScreenViewModel.getAllMovies(genreId = 14)
            homeScreenViewModel.getAllGenres()
        }
    }

    override fun onItemSelected(position: Int) {
        highlightIndicator(position)
    }

    private fun highlightIndicator(position: Int) {
        for (i in indicatorViews.indices) {
            indicatorViews[i].setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    if (i == position) R.drawable.dot_active else R.drawable.dot_inactive
                )
            )
        }
        postersSliderAdapter.setSelectedItem(position)
    }

    private fun startAutoSlide() {
        handler.postDelayed(sliderRunnable, 4000)
    }

}
