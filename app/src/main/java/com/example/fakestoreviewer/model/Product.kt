package com.example.fakestoreviewer.model

//class of the data we get as json structure
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val images: List<String>
)