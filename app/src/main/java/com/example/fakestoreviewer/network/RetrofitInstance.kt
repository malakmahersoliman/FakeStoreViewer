package com.example.fakestoreviewer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// This object holds a singleton instance of Retrofit.
// It is used to make network requests to the FakeStore API.
object RetrofitInstance {
    // Lazy-initialized API service
    // This connects your Retrofit client to the ProductApiService interface
    val api: ProductApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
    }
}
