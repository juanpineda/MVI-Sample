package com.juanp.mvisample.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CompanyDb::class], version = 1)
abstract class CompanyDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            CompanyDatabase::class.java,
            "company-db"
        ).build()
    }

    abstract fun companyDao(): CompanyDao
}