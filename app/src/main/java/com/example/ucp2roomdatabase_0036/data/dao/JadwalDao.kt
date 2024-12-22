package com.example.ucp2roomdatabase_0036.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import com.example.ucp2roomdatabase_0036.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

@Dao
interface JadwalDao {

    //getAll Jadwal
    @Query("SELECT * FROM Jadwal ORDER BY id ASC")
    fun getAllJadwal(): Flow<List<Jadwal>>

    @Query("SELECT * FROM Jadwal WHERE id = :id")
    fun getJadwalByid(id: String): Flow<Jadwal?>

    @Query("SELECT * FROM dokter ORDER BY NamaDokter DESC")
    fun getAllNamaDokter(): Flow<List<Dokter>>


    @Insert
    suspend fun InsertJadwal(jadwal: Jadwal)

    @Delete
    suspend fun DeleteJadwal(jadwal: Jadwal)

    @Update
    suspend fun UpdateJadwal(jadwal: Jadwal)

}