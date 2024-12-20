package com.example.ucp2roomdatabase_0036.dependenciesinjection

import android.content.Context
import com.example.ucp2roomdatabase_0036.data.database.RumahSakitDatabase
import com.example.ucp2roomdatabase_0036.repository.LocalRepositoryDokter
import com.example.ucp2roomdatabase_0036.repository.LocalRepositoryJadwal
import com.example.ucp2roomdatabase_0036.repository.RepositoryDokter
import com.example.ucp2roomdatabase_0036.repository.RepositoryJadwal

interface InterfaceContainerApp{
    val repositoryDokter : RepositoryDokter
    val repositoryJadwal : RepositoryJadwal
}

class ContainerApp(private val context: Context): InterfaceContainerApp{
    override val repositoryDokter: RepositoryDokter by lazy {
        LocalRepositoryDokter(RumahSakitDatabase.getDatabase(context).DokterDao())
    }
    override val repositoryJadwal: RepositoryJadwal by lazy {
        LocalRepositoryJadwal(RumahSakitDatabase.getDatabase(context).JadwalDao())
    }
}