package com.example.myapplication.features.registerScreen

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityRegisterscreenBinding
import com.example.myapplication.features.homeScreen.HomeScreenActivity
import com.example.myapplication.features.registerScreen.presentation.viewmodel.RegisterScreenViewModel
import com.example.myapplication.features.registerScreen.presentation.viewmodel.RegisterScreenViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class RegisterScreenActivity : AppCompatActivity() {

    //region properties
    private lateinit var binding: ActivityRegisterscreenBinding
    private lateinit var viewModel: RegisterScreenViewModel
    //endregion

    //region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialViewModel()
        checkUserLoggedIn()
    }
    //endregion

    //region methods
    private fun checkUserLoggedIn() {
        if (viewModel.isUserLoggedIn()) {
            goHome()
        } else {
            initialBinding()
            configListener()
            configViewModel()
        }
    }

    private fun goHome() {
        Intent(this, HomeScreenActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun configViewModel() {
        viewModel.registerStatus.observe(this) {
            if (it.isNotEmpty()) {
                viewModel.saveToken(it)
                goHome()
            }
        }
    }

    private fun configListener() {
        binding.apply {
            submitBtn.setOnClickListener {
                val email = emailEditText.text.toString()
                val name = nameEditText.text.toString()
                val studentNumber = studentNumberEditText.text.toString()
                val password = passwordEditText.text.toString()
                if (email.isEmpty() || name.isEmpty() ||
                    studentNumber.isEmpty() || password.isEmpty()
                ) {
                    Snackbar.make(it, viewModel.dataError, Snackbar.LENGTH_LONG).show()

                } else if (checkInput(name, studentNumber, email, password)){
                    viewModel.email = email
                    viewModel.name = name
                    viewModel.studentNumber = studentNumber
                    viewModel.password = password
                    viewModel.register()
                }
                else {
                    Snackbar.make(it, "Invalid login!", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun checkInput(name: String, studentNumber: String, email: String, password: String): Boolean {
        val namePattern = "^[a-zA-Z\\s]{1,20}$"
        val studentNumberPattern = "^[0-9]{7,8}$"
        val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$"

        if (name.isEmpty()) {
            binding.nameEditText.error = "name can't be empty"
            return false
        }
        if (!Pattern.matches(namePattern, name)) {
            binding.nameEditText.error = "name should be 1 to 20 letters"
            return false
        }

        if (studentNumber.isEmpty()) {
            binding.studentNumberEditText.error = "student number can't be empty"
            return false
        }
        if (!Pattern.matches(studentNumberPattern, studentNumber)) {
            binding.studentNumberEditText.error = "student number should be 7 to 8 numbers"
            return false
        }

        if (email.isEmpty()) {
            binding.emailEditText.error = "email can't be empty"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEditText.error = "email format is invalid"
            return false
        }

        if (password.isEmpty()) {
            binding.passwordEditText.error = "password can't be empty"
            return false
        }
        if (!Pattern.matches(passwordPattern, password)) {
            binding.passwordEditText.error = "password should be at least 6 characters, containing at least 1 letter and 1 digit"
            return false
        }
        return true
    }

    private fun initialBinding() {
        binding = ActivityRegisterscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initialViewModel() {
        viewModel =
            ViewModelProvider(this, RegisterScreenViewModelFactory())[RegisterScreenViewModel::class.java]
    }
    //endregion
}