package com.example.fakestoreviewer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreviewer.model.Product
import com.example.fakestoreviewer.model.ResultState
import com.example.fakestoreviewer.network.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// ProductViewModel handles fetching the product list from the API
// and exposes a ResultState to represent loading, success, or error.
class ProductViewModel : ViewModel() {

    // Public state observed by the UI: can be Loading, Success, or Error
    var productState by mutableStateOf<ResultState<List<Product>>>(ResultState.Loading)
        private set
    //  fetch products when the ViewModel is created
    init {
        fetchProducts()
    }

    // Makes a network request using Retrofit to get products
    fun fetchProducts() {
        productState = ResultState.Loading
        viewModelScope.launch {
            try {
                // Call API and get list of products
                val products = RetrofitInstance.api.getProducts()
                // On success, update the state with the product list
                productState = ResultState.Success(products)
            } catch (e: Exception) {
                // On failure, update state with the error message
                productState = ResultState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}
