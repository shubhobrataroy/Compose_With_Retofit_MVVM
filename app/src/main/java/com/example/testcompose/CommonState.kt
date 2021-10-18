package com.example.testcompose

import java.lang.Exception

sealed class CommonState <out T :Any> {
    object Fetching : CommonState<Nothing>()
    data class Error(val exception: Exception) : CommonState<Nothing>()
    data class Success<out T :Any>(val data: T) : CommonState<T>()
}