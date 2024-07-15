package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivitySearchscreenBinding
import com.example.myapplication.features.homeScreen.presentation.ui.adapters.MovieAdapter
import com.example.myapplication.features.searchScreen.presentation.viewmodel.SearchScreenViewModel
import com.example.myapplication.features.searchScreen.presentation.viewmodel.SearchScreenViewModelFactory


class SearchScreenActivity : AppCompatActivity() {

    //region Properties
    private lateinit var searchScreenBinding: ActivitySearchscreenBinding
    private lateinit var searchScreenViewModel: SearchScreenViewModel
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
        searchScreenBinding = ActivitySearchscreenBinding.inflate(layoutInflater)
        setContentView(searchScreenBinding.root)
    }

    private fun initialViewModel() {
        searchScreenViewModel = ViewModelProvider(this, SearchScreenViewModelFactory())[SearchScreenViewModel::class.java]
    }

    private fun configViewModel() {

        searchScreenViewModel.movies.observe(this) { movies ->

            val moviesList = movies.data
            val movieAdapter = MovieAdapter(moviesList)
            searchScreenBinding.resultMoviesRecycler.adapter = movieAdapter
            searchScreenBinding.resultMoviesRecycler.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }


        SearchScreenViewModel.searchKeyword.observe(this) { searchKeyword ->
            searchScreenViewModel.getAllMoviesBySearchKeyword(searchKeyword)
        }


        searchScreenBinding.homeIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    private fun callAPI() {

        val searchEditText = searchScreenBinding.searchBarCard.findViewById<EditText>(R.id.search_edit_text)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchKeyword = s.toString()
                searchScreenViewModel.getAllMoviesBySearchKeyword(searchKeyword)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
