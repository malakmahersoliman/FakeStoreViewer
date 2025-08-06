package com.example.fakestoreviewer.network

import com.example.fakestoreviewer.model.Product
import retrofit2.http.GET

// The FakeStore API provides a list of products at this endpoint:
// https://api.escuelajs.co/api/v1/products
interface ProductApiService {
    // GET request to fetch all products
    // This will return a List<Product> once the API call succeeds
    @GET("products")
    suspend fun getProducts(): List<Product>
}