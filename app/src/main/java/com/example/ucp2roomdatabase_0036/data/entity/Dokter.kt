package com.example.ucp2roomdatabase_0036.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dokter")
data class Dokter(
    @PrimaryKey
    val Id : String,
    val NamaDokter : String,
    val Spesialis : String,
    val Klinik : String,
    val NoHpDokter : String,
    val JamKerja : String
)
