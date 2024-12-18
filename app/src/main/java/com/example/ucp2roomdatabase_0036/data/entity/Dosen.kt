package com.example.ucp2roomdatabase_0036.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dosen")
data class Dosen(
    @PrimaryKey
    val Id : String,
    val NamaDosen : String,
    val Spesialis : String,
    val Klinik : String,
    val NoHpDosen : String,
    val JamKerja : String
)
