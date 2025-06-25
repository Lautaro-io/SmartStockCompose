package com.chelo.smartstock.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.smartstock.data.api.ProductDataResponse
import com.chelo.smartstock.data.api.RetrofitInstance
import com.chelo.smartstock.data.entities.ProductEntity
import com.chelo.smartstock.data.local.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    val allProducts = repository.getAllProducts()


    var nameProduct by mutableStateOf("")
        private set
    var codeBar by mutableStateOf("")
        private set

    var countProduct by mutableStateOf("")
        private set

    var image by mutableStateOf("")
        private set


    fun onNameChanged(value: String) {
        nameProduct = value
    }


    fun onCodebarChanged(value: String){
        codeBar = value
    }

    fun onCountChanged( value : String  ){
        countProduct = value
    }

    private val _selectedBranch = MutableStateFlow<Long?>(null)
    val selectedBranch: StateFlow<Long?> = _selectedBranch


    private val _filteredProducts = MutableStateFlow<List<ProductEntity>>(emptyList())
    val filteredProducts: StateFlow<List<ProductEntity>?> = _filteredProducts

    private val _productResult = MutableStateFlow<ProductDataResponse?>(null)
    val productResult: StateFlow<ProductDataResponse?> = _productResult.asStateFlow()


    fun insertProduct(product: ProductEntity) {
        viewModelScope.launch {
            repository.insertProduct(product)
        }
    }

    fun deleteProduct(product: ProductEntity) {
        viewModelScope.launch {
            repository.deleteProduct(product)
        }
    }

    fun updateProduct(product: ProductEntity) {
        viewModelScope.launch {
            repository.updateProduct(product)
        }
    }

    fun selectBranch(branchId: Long) {
        _selectedBranch.value = branchId
    }


    fun productsByBranch() {
        viewModelScope.launch {
            val branchId = _selectedBranch.value ?: 0
            val products = repository.getBranchProducts(branchId).collect { product ->
                _filteredProducts.value = product
            }

        }
    }


    fun getProductByCode(code: String) {
        codeBar = code
        viewModelScope.launch {
            val result =  try {
              RetrofitInstance.api.getProductByCode(codeBar).body()
            } catch (e: Exception) {
                 null
            }
            _productResult.value = result
            nameProduct = _productResult.value?.product?.nameProduct ?: "No encontrado"
            image = _productResult.value?.product?.imageProduct ?: ""
        }
    }

}