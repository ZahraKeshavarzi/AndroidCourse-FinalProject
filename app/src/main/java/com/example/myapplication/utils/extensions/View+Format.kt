package com.example.myapplication.utils.extensions

import android.view.View

fun View.gone() {
    this.visibility = View.GONE
}

fun View.show() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

