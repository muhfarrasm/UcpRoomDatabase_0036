package com.example.ucp2roomdatabase_0036.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

@Dao
interface DokterDao {

    //getAll Dokter
    @Query("SELECT * FROM dokter ORDER BY Id ASC")
    fun getAllDokter(): Flow<List<Dokter>>

    @Query("SELECT * FROM dokter WHERE Id = :Id")
    fun getDokterById(Id: String): Flow<Dokter?>

    @Insert
    suspend fun InsertDokter(dokter: Dokter)



}