package com.example.ucp2roomdatabase_0036.repository

import com.example.ucp2roomdatabase_0036.data.dao.DokterDao
import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDokter(
    private val dokterDao: DokterDao
): RepositoryDokter{
    override suspend fun InsertDokter(dokter: Dokter) {
        dokterDao.InsertDokter(dokter)
    }
    override fun getAllDokter(): Flow<List<Dokter>> {
        return dokterDao.getAllDokter()

    }
    override fun getDokterById(Id: String): Flow<Dokter?> {
        return dokterDao.getDokterById(Id)
    }



}