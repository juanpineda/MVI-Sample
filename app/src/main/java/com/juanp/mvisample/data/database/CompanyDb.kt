package com.juanp.mvisample.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyDb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val address: String,
    val nit: String,
    val phone: String
)