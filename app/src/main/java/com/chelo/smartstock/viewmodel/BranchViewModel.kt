package com.chelo.smartstock.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.smartstock.data.entities.BranchEntity
import com.chelo.smartstock.data.local.repositories.BranchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BranchViewModel @Inject constructor(private val repository: BranchRepository) : ViewModel(){

    val allBranches = repository.getAllBranches()

    var newBranch  by  mutableStateOf("")
        private set

    var selectedBranch by mutableStateOf<BranchEntity?>(null)

    fun onNameBranchChange(value : String) { newBranch = value}
    fun addBranch(branch: BranchEntity){
        viewModelScope.launch {
            repository.insertBranch(branch)
        }
    }

    fun selectBranch(value : BranchEntity){
        selectedBranch = value
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