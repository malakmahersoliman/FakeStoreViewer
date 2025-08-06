package com.example.fakestoreviewer.model


// Sealed class used to represent the result of a network call or async operation.
// It can be one of: Loading, Success, or Error.
sealed class ResultState<out T> {
    // Loading state: used while data is being fetched
    object Loading : ResultState<Nothing>()
    // Success state: wraps the actual result (e.g., list of products)
    data class Success<T>(val data: T) : ResultState<T>()
    // Error state: contains a message describing the failure
    data class Error(val message: String) : ResultState<Nothing>()
}
