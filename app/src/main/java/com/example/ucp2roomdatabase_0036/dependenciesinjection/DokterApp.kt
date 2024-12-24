package com.example.ucp2roomdatabase_0036.dependenciesinjection

import android.content.Context
import com.example.ucp2roomdatabase_0036.data.database.RumahSakitDatabase
import com.example.ucp2roomdatabase_0036.repository.LocalRepositoryDokter
import com.example.ucp2roomdatabase_0036.repository.RepositoryDokter

interface InterfaceDokterApp{
    val repositoryDokter : RepositoryDokter

}

class DokterApp(private val context: Context): InterfaceDokterApp {
    override val repositoryDokter: RepositoryDokter by lazy {
        LocalRepositoryDokter(RumahSakitDatabase.getDatabase(context).DokterDao())
    }
}
