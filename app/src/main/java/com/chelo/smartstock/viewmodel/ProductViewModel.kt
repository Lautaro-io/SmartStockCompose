package com.chelo.smartstock.viewmodel

import android.util.Log
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {


    var nameProduct by mutableStateOf("")
        private set
    var codeBar by mutableStateOf("")
        private set

    var countProduct by mutableStateOf("")
        private set

    var image by mutableStateOf("")
        private set


    var expireDate by mutableStateOf("")
        private set

    fun onExpireDateChange(value: String){
        expireDate  = value
    }

    fun onNameChanged(value: String) {
        nameProduct = value
    }


    fun onCodebarChanged(value: String){
        codeBar = value
    }

    fun onImageChanged(value : String){
        image = value
    }
    fun onCountChanged( value : String  ){
        countProduct = value
    }

    private val _selectedBranch = MutableStateFlow<Long?>(null)
    val selectedBranch: StateFlow<Long?> = _selectedBranch


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




    fun saveProduct(productId: Long?){
        viewModelScope.launch { 
            val product = ProductEntity(
                productId = productId ?: 0,
                nameProduct = nameProduct,
                count = countProduct.toInt() ,
                expireDate = expireDate,
                codeBar = codeBar,
                image = image,
                branchFkId = selectedBranch.value!!
            )
            if (productId != null)
                updateProduct(product)
            else
                insertProduct(product)
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
            Log.i("CHELO",image.toString())
        }
    }


    fun loadProduct(productId : Long? ){
        viewModelScope.launch {
            val product = repository.getProductById(productId!!)
            nameProduct = product.nameProduct
            codeBar = product.codeBar
            countProduct = product.count.toString()
            image = product.image
            expireDate = product.expireDate
            _selectedBranch.value = product.branchFkId

        }
    }


    fun isExpired(product: ProductEntity) : Boolean{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(product.expireDate , formatter) < LocalDate.now()
    }

}