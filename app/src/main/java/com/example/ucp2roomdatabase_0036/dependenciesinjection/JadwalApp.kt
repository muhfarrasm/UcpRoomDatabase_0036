package com.example.ucp2roomdatabase_0036.dependenciesinjection

import android.content.Context
import com.example.ucp2roomdatabase_0036.data.database.RumahSakitDatabase
import com.example.ucp2roomdatabase_0036.repository.LocalRepositoryJadwal
import com.example.ucp2roomdatabase_0036.repository.RepositoryJadwal

interface InterfaceJadwalApp{
    val repositoryJadwal : RepositoryJadwal
}

class JadwalApp(private val context: Context): InterfaceJadwalApp{
    override val repositoryJadwal: RepositoryJadwal by lazy {
        LocalRepositoryJadwal(RumahSakitDatabase.getDatabase(context).JadwalDao())
    }
}