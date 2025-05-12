package com.chelo.smartstock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.smartstock.data.entities.UserEntity
import com.chelo.smartstock.data.local.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {


    fun addUser(user: UserEntity, onResult: (Long) -> Unit) {
        viewModelScope.launch {
            val id = repository.insertUser(user)
            onResult(id)

        }
    }

    fun deleteUser(user: UserEntity) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }

    fun updateUser(user: UserEntity) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }

}