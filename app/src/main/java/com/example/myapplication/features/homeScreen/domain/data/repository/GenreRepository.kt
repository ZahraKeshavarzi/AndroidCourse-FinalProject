package com.example.myapplication.features.homeScreen.domain.data.repository

import com.example.myapplication.features.homeScreen.domain.data.model.Genre

class GenreRepository {
    fun getAllGenres(): ArrayList<Genre> {
        return arrayListOf(
            Genre("Crime"),
            Genre("Drama"),
            Genre("Action"),
            Genre("Biography"),
            Genre("History"),
            Genre("Adventure"),
            Genre("Fantasy"),
            Genre("Western"),
            Genre("comedy"),
            Genre("Sci-Fi"),
            Genre("Mystery"),
            Genre("Thriller"),
            Genre("Family"),
            Genre("War"),
            Genre("Animation"),
            Genre("Romance"),
            Genre("Horror"),
            Genre("Music"),
            Genre("Film-Noir"),
            Genre("Musical"),
            Genre("Sport"),

        )
    }
}