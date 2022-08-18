package com.juanp.mvisample.data

import com.juanp.mvisample.data.database.CompanyDb

fun CompanyDb.toDomainCompany(): Company =
    Company(
        id = id,
        name = name,
        address = address,
        nit = nit,
        phone = phone
    )

fun Company.toRoomCompany(): CompanyDb = CompanyDb(
    id,
    name,
    address,
    nit,
    phone
)