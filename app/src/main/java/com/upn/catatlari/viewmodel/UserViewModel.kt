package com.upn.catatlari.viewmodel

import androidx.lifecycle.*
import com.upn.catatlari.model.User
import com.upn.catatlari.Repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<User?>()
    val loginResult: LiveData<User?> = _loginResult

    fun register(user: User) {
        viewModelScope.launch {
            repository.register(user)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = repository.login(email, password)
        }
    }
}