package com.example.ucp2roomdatabase_0036

import android.app.Application
import com.example.ucp2roomdatabase_0036.dependenciesinjection.DokterApp
import com.example.ucp2roomdatabase_0036.dependenciesinjection.JadwalApp

class AyoSehatApp : Application(){
    lateinit var dokterApp: DokterApp
    lateinit var jadwalApp: JadwalApp

    override fun onCreate() {
        super.onCreate()
        dokterApp = DokterApp(this)
        jadwalApp = JadwalApp(this)

    }
}