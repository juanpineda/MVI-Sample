package com.juanp.mvisample.data.database

import android.content.Context
import com.juanp.mvisample.data.Company
import com.juanp.mvisample.data.toDomainCompany
import com.juanp.mvisample.data.toRoomCompany
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RoomDataSource(context: Context) : LocalDataSource {

    private val companyDao = CompanyDatabase.build(context).companyDao()

    override suspend fun saveCompany(company: Company): Long =
        withContext(Dispatchers.IO) { companyDao.insertCompanies(company.toRoomCompany()) }

    override suspend fun getPopularCompany(): Flow<List<Company>> = withContext(Dispatchers.IO) {
        companyDao.getAll().map { list -> list.map { it.toDomainCompany() } }
    }

    override suspend fun findById(id: Int): Company = withContext(Dispatchers.IO) {
        companyDao.findById(id).toDomainCompany()
    }

    override suspend fun update(company: Company) {
        withContext(Dispatchers.IO) { companyDao.updateCompany(company.toRoomCompany()) }
    }

    override suspend fun delete(id: Int) {
        withContext(Dispatchers.IO) { companyDao.deleteCompany(id) }
    }
}