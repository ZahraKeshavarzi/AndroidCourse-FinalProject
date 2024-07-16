package com.example.myapplication.features.registerScreen.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.registerScreen.domain.data.model.RegisterResponse
import com.example.myapplication.features.registerScreen.domain.data.model.User
import com.example.myapplication.features.registerScreen.domain.data.repository.RegisterRepository
import com.example.myapplication.sharedComponents.api.API
import kotlinx.coroutines.launch


class RegisterScreenViewModel(private val repository: RegisterRepository) : ViewModel() {

    //region Properties
    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> get() = _registerResponse
    //endregion


    //region Methods
    fun registerUser(user: User) {
        viewModelScope.launch {
            val response = repository.registerUser(user)
            if (response.isSuccess) {
                response.onSuccess {
                    _registerResponse.postValue(it)
                }.onFailure {
                    _registerResponse.postValue(null)
                }
            }
        }
    }
    //endregion

}

class ResponseModule {
    companion object {
        val watchRepository: RegisterRepository by lazy {
            RegisterRepository(API.registerScreenService)
        }
    }
}

class RegisterScreenViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterScreenViewModel::class.java)) {
            return RegisterScreenViewModel(ResponseModule.watchRepository) as T
        }
        throw java.lang.IllegalArgumentException("wrong dependency")
    }
}