//package com.example.myapplication.features.registerScreen
//
//import android.content.Intent
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.util.Log
//import android.util.Patterns
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//import com.example.myapplication.R
//import com.example.myapplication.databinding.ActivityRegisterscreenBinding
//import com.example.myapplication.features.homeScreen.HomeScreenActivity
//import com.example.myapplication.features.registerScreen.domain.data.Constants
//import com.example.myapplication.features.registerScreen.domain.data.model.User
//import com.example.myapplication.features.registerScreen.presentation.viewmodel.RegisterScreenViewModel
//import com.example.myapplication.features.registerScreen.presentation.viewmodel.RegisterScreenViewModelFactory
//import com.example.myapplication.utils.extensions.putString
//import java.security.MessageDigest
//import java.util.regex.Pattern
//
//class RegisterScreenActivity : AppCompatActivity() {
//
//    //region properties
//    private lateinit var binding: ActivityRegisterscreenBinding
//    private lateinit var sharedPreferences: SharedPreferences
//    private lateinit var viewModel: RegisterScreenViewModel
//    //endregion
//
//    //region lifecycle
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityRegisterscreenBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        initialBinding()
//        initialSharedPreference()
//        initialViewModel()
//        initialButton()
//    }
//    //endregion
//
//    //region methods
//    private fun initialBinding() {
//        binding.verificationIcon.setImageResource(R.drawable.fingerprint_icon)
//    }
//
//    private fun initialSharedPreference() {
//        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_KEY, MODE_PRIVATE)
//    }
//
//    private fun initialViewModel() {
//        viewModel = ViewModelProvider(this, RegisterScreenViewModelFactory())[RegisterScreenViewModel::class.java]
//    }
//
//    private fun initialButton() {
//        binding.submitBtn.setOnClickListener {
//            val name = binding.nameEditText.text.toString()
//            val studentNumber = binding.studentNumberEditText.text.toString()
//            val email = binding.emailEditText.text.toString()
//            val password = binding.passwordEditText.text.toString()
//
//            if (checkInput(name, studentNumber, email, password)) {
//                val encryptedPassword = encryptPassword(password)
//                val user = User(email, name, studentNumber, encryptedPassword)
//                Log.d("RegisterScreenActivity", "Submitting user: $user")
//                viewModel.registerUser(user)
//            }
//        }
//
//        viewModel.registerResponse.observe(this) { response ->
//            Log.d("RegisterScreenActivity", "Received response: $response")
//            if (response.status == 200) {
//                Log.e("Reached here", "Reached here")
//                saveUserToken()
//                Log.d("RegisterScreenActivity", "Navigating to HomeScreenActivity")
//                val intent = Intent(this, HomeScreenActivity::class.java)
//                startActivity(intent)
//            } else {
//                Log.e("RegisterScreenActivity", "Failed to register user.")
//            }
//        }
//    }
//
//    private fun checkInput(name: String, studentNumber: String, email: String, password: String): Boolean {
//        val namePattern = "^[a-zA-Z\\s]{1,20}$"
//        val studentNumberPattern = "^[0-9]{7,8}$"
//        val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$"
//
//        if (name.isEmpty()) {
//            binding.nameEditText.error = "name can't be empty"
//            return false
//        }
//        if (!Pattern.matches(namePattern, name)) {
//
//            binding.nameEditText.error = "name should be 1 to 20 letters"
//            return false
//        }
//
//        if (studentNumber.isEmpty()) {
//            binding.studentNumberEditText.error = "student number can't be empty"
//            return false
//        }
//        if (!Pattern.matches(studentNumberPattern, studentNumber)) {  // Corrected this line
//            binding.studentNumberEditText.error = "student number should be 7 to 8 numbers"
//            return false
//        }
//
//        if (email.isEmpty()) {
//            binding.emailEditText.error = "email can't be empty"
//            return false
//        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            binding.emailEditText.error = "email format is invalid"
//            return false
//        }
//
//        if (password.isEmpty()) {
//            binding.passwordEditText.error = "password can't be empty"
//            return false
//        }
//        if (!Pattern.matches(passwordPattern, password)) {  // Corrected this line
//            binding.passwordEditText.error = "password should be at least 6 characters, containing at least 1 letter and 1 digit"
//            return false
//        }
//        return true
//    }
//
//    private fun encryptPassword(password: String): String {
//        val md = MessageDigest.getInstance("MD5")
//        val bytes = md.digest(password.toByteArray())
//        return bytes.joinToString("") { "%02x".format(it) }
//    }
//
//    private fun saveUserToken(){
//        Log.d("RegisterScreenActivity", "Saving user token")
//        sharedPreferences.putString(Constants.USER_TOKEN_KEY, "user")
//    }
//    //endregion
//}
