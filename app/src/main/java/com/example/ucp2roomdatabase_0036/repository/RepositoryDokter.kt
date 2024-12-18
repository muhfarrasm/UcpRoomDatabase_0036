package com.example.ucp2roomdatabase_0036.repository

import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

interface RepositoryDokter {

    // method ini untuk memanggil fungsi geetAllMahasiswa dari mahasiswaDao untuk
    //mendapatkan semua data dlm bentuk aliran
    fun getAllDokter() : Flow<List<Dokter>>
    //method ini untuk memanggil fungsi geetAllMahasiswa dari mahasiswaDao berdasarkan NIM
    fun getDokterById(Id: String): Flow<Dokter?>

    //Method ini memanfaatkan fungsi insertMahasiswa dari MahasiswaDao
    suspend fun InsertDokter(dokter: Dokter)
}