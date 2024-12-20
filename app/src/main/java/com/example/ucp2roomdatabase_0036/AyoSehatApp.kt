package com.example.ucp2roomdatabase_0036

import android.app.Application
import com.example.ucp2roomdatabase_0036.dependenciesinjection.ContainerApp

class AyoSehatApp : Application(){
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}