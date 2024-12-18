package com.example.ucp2roomdatabase_0036.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

@Dao
interface DokterDao {

    //getAll Dokter
    @Query("SELECT * FROM Dokter ORDER BY Id ASC")
    fun getAllDokter(): Flow<List<Dokter>>

    @Query("SELECT * FROM Dokter WHERE Id = :Id")
    fun getDokterById(Id: String): Flow<Dokter?>

    @Insert
    suspend fun insertDokter(Dokter: Dokter)
}