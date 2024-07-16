//package com.example.myapplication.features.registerScreen.presentation.viewmodel
//
//import RegisterRepository
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewModelScope
//import com.example.myapplication.features.registerScreen.domain.data.Constants
//import com.example.myapplication.features.registerScreen.domain.data.model.RegisterPostBody
//import com.example.myapplication.features.registerScreen.domain.data.repository.SharedPreferencesRepository
//import com.example.myapplication.sharedComponents.api.API
//import kotlinx.coroutines.launch
//import java.security.MessageDigest
//
//class RegisterViewModel(
//    private val registerRepository: RegisterRepository,
//    private val sharedPreferencesRepository: SharedPreferencesRepository
//) : ViewModel() {
//
//    var email = ""
//    var name = ""
//    var studentNumber = ""
//    var password = ""
//    val dataError get() = Constants.DATA_EMPTY_ERROR
//
//    private val _registerStatus = MutableLiveData<String>()
//    val registerStatus: LiveData<String> get() = _registerStatus
//
//    fun register() {
//        val hashedPassword = hashPassword(password)
//        val body = RegisterPostBody(email, name, studentNumber, hashedPassword)
//        viewModelScope.launch {
//            registerRepository.register(body).onSuccess {
//                _registerStatus.postValue(it.description.toString())
//            }
//        }
//    }
//
//    fun saveToken(token: String) =
//        sharedPreferencesRepository.putString(Constants.USER_TOKEN_KEY, token)
//
//    private fun hashPassword(password: String): String {
//        val md5 = MessageDigest.getInstance("MD5")
//        return md5.digest(password.toByteArray()).joinToString("") { "%02x".format(it) }
//    }
//
//    fun isUserLoggedIn(): Boolean {
//        return sharedPreferencesRepository.getString(Constants.USER_TOKEN_KEY).isNotEmpty()
//    }
//}
//
//class RegisterModule {
//    companion object {
//        val registerRepository: RegisterRepository by lazy {
//            RegisterRepository(API.registerScreenService)
//        }
//        val sharedPreferencesRepository: SharedPreferencesRepository by lazy {
//            SharedPreferencesRepository(sharedPreferencesRepository)
//        }
//    }
//}
//
//
//class RegisterViewModelFactory : ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
//            return RegisterViewModel(
//                RegisterModule.registerRepository,
//                RegisterModule.sharedPreferencesRepository
//            ) as T
//        }
//        throw java.lang.IllegalArgumentException("wrong dependency")
//    }
//}
