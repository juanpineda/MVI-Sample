package com.juanp.mvisample.data

data class Company(
    val name: String,
    val address: String,
    val nit: String,
    val phone: String,
    val id: Int = 0
    )