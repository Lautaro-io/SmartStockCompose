package com.chelo.smartstock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.smartstock.data.entities.BranchEntity
import com.chelo.smartstock.data.local.repositories.BranchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BranchViewModel @Inject constructor(private val repository: BranchRepository) : ViewModel(){

    fun addBranch(branch: BranchEntity){
        viewModelScope.launch {
            repository.insertBranch(branch)
        }
    }

    fun deleteBranch(branch: BranchEntity){
        viewModelScope.launch {
            repository.deleteBranch(branch)
        }
    }

    fun updateBranch(branch: BranchEntity){
        viewModelScope.launch {
            repository.updateBranch(branch)
        }
    }
}