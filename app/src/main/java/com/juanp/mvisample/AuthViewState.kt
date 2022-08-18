package com.juanp.mvisample

import com.juanp.mvisample.data.Company

data class AuthViewState(
    val error: ResultState? = null,
    val result: List<Company>? = null
)
enum class ResultState{
    SUCCESS_SAVE,
    SUCCESS_DELETE,
    SUCCESS_EDIT,
    ERROR
}