package com.chelo.smartstock.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.smartstock.data.entities.ProductEntity
import com.chelo.smartstock.data.local.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    val allProducts = repository.getAllProducts()

    private val _selectedBranch = MutableStateFlow<Long?>(null)

    val selectedBranch: StateFlow<Long?> = _selectedBranch


    //Esto que se hace acontinuacion se llama encapsulamiento del estado mutable, es un patron de disenio para evitar que la UI acceda directamente al valor de selectedBranch
    // _filteredProducts Este va a ser controlado por el viewmodel para modificar su valor en caso de que el usuario quiera filtrar por sucursal
    // filteredProductsEste es inmutable para la ui

    private val _filteredProducts =
        MutableStateFlow<List<ProductEntity>>(emptyList())

    val filteredProducts: StateFlow<List<ProductEntity>?> = _filteredProducts


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


    //Que paso en esta funcion, getBranchProducts devuelve un Flow<List<PRoduct>> y _filteredProducts espera un List<Product>>
    // en especifico,
    // lo que se hizo fue dentro de la corrutina collectar los resultados de la funcion getBranchProducts
    // y asignarlos en _filteredProducts
    fun productsByBranch() {
        viewModelScope.launch {
            val branchId = _selectedBranch.value ?: 0
            val products = repository.getBranchProducts(branchId).collect { product ->
                _filteredProducts.value = product
            }

        }
    }


}