package com.example.ucp2roomdatabase_0036.repository


import com.example.ucp2roomdatabase_0036.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

interface RepositoryJadwal {

    // method ini untuk memanggil fungsi geetAllMahasiswa dari mahasiswaDao untuk
    //mendapatkan semua data dlm bentuk aliran
    fun getAllJadwal() : Flow<List<Jadwal>>
    //method ini untuk memanggil fungsi geetAllMahasiswa dari mahasiswaDao berdasarkan NIM
    fun getJadwalByid(id: String): Flow<Jadwal?>

    //Method ini memanfaatkan fungsi insertMahasiswa dari MahasiswaDao
    suspend fun InsertJadwal(jadwal: Jadwal)

    //Method ini memanfaatkan fungsi deleteMahasiswa dari MahasiswaDao
    suspend fun DeleteJadwal(jadwal: Jadwal)

    //Method ini memanfaatkan fungsi updateMahasiswa dari MahasiswaDao
    suspend fun UpdateJadwal(jadwal: Jadwal)


}