package com.chelo.smartstock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.smartstock.data.entities.BranchEntity
import com.chelo.smartstock.data.local.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MainViewmodel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {


    private val _state = MutableStateFlow<Boolean>(false)
    val state = _state.asStateFlow()

    private val _selectedBranch = MutableStateFlow<BranchEntity?>(null)
    val selectedBranch = _selectedBranch

    val allProduct = productRepository.getAllProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(2000), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredProducts = selectedBranch.flatMapLatest {
        productRepository.getProductsByBranch(it?.branchId ?: -1)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(2000), emptyList())

    val products =
        combine(state, allProduct, filteredProducts) { state, allProducts, filteredProducts ->
            if (state) filteredProducts else allProducts
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(200), emptyList())


    fun changeState(value: Boolean) {
        _state.value = value
    }



    fun selectBranch(value: BranchEntity) {
        _selectedBranch.value = value
    }
}