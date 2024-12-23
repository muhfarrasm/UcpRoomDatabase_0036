package com.example.ucp2roomdatabase_0036.repository


import com.example.ucp2roomdatabase_0036.data.dao.JadwalDao
import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import com.example.ucp2roomdatabase_0036.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

class LocalRepositoryJadwal(
    private val jadwalDao: JadwalDao
): RepositoryJadwal{
    override suspend fun InsertJadwal(jadwal: Jadwal) {
        jadwalDao.InsertJadwal(jadwal)
    }

    override suspend fun UpdateJadwal(jadwal: Jadwal) {
        jadwalDao.UpdateJadwal(jadwal)
    }

    override suspend fun deletejdwl(jadwal: Jadwal) {
        jadwalDao.UpdateJadwal(jadwal)
    }
    override fun getAllJadwal(): Flow<List<Jadwal>> {
        return jadwalDao.getAllJadwal()

    }

    override fun getJadwalByid(id: String): Flow<Jadwal?> {
        return jadwalDao.getJadwalByid(id)
    }

    override fun getAllNamaDokter(): Flow<List<Dokter>> {
        return jadwalDao.getAllNamaDokter()
    }
}