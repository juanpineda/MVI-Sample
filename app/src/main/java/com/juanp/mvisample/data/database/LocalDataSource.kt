package com.juanp.mvisample.data.database

import com.juanp.mvisample.data.Company
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveCompany(company: Company):Long
    suspend fun getPopularCompany(): Flow<List<Company>>
    suspend fun findById(id: Int): Company
    suspend fun update(company: Company)
    suspend fun delete(id: Int)
}