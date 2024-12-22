package com.example.ucp2roomdatabase_0036.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2roomdatabase_0036.data.dao.DokterDao
import com.example.ucp2roomdatabase_0036.data.dao.JadwalDao
import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import com.example.ucp2roomdatabase_0036.data.entity.Jadwal

@Database(entities = [Dokter ::class, Jadwal ::class], version = 1, exportSchema = false)
abstract class RumahSakitDatabase : RoomDatabase(){
    //aksess database
    abstract fun DokterDao(): DokterDao
    abstract fun JadwalDao(): JadwalDao

    companion object{
        @Volatile // nilai variable instance selalu sama
        private var Instance:RumahSakitDatabase? = null

        fun getDatabase(context: Context): RumahSakitDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    RumahSakitDatabase::class.java, //class database
                    "RumahSakitDatabase" //nama database
                )
                    .build().also { Instance = it }
            })
        }
    }
}