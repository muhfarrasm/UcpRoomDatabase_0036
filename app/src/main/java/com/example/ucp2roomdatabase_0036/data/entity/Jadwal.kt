package com.example.ucp2roomdatabase_0036.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jadwal")
data class Jadwal(
    @PrimaryKey
    val id : String,
    val NamaDokter : String,
    val NamaPasien : String,
    val NoHp : String,
    val TglKonsultasi : String,
    val Status : String
)