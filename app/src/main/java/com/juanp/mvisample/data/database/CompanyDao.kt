package com.juanp.mvisample.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyDao {

    @Query("SELECT * FROM CompanyDb")
    fun getAll(): Flow<List<CompanyDb>>

    @Query("SELECT * FROM CompanyDb WHERE id = :id")
    fun findById(id: Int): CompanyDb

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCompanies(company: CompanyDb):Long

    @Update
    fun updateCompany(company: CompanyDb)

    @Query("DELETE FROM CompanyDb WHERE id = :id")
    fun deleteCompany(id: Int)
}