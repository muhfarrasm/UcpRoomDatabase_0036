package com.example.ucp2roomdatabase_0036.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2roomdatabase_0036.AyoSehatApp


//import com.example.ucp2roomdatabase_0036.ui.viewmodel.jadwal.DetailJadwalViewModel



object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            DokterViewModel(
                ayoSehatApp().dokterApp.repositoryDokter
            )
        }

        initializer {
            HomeDokterViewModel(
                ayoSehatApp().dokterApp.repositoryDokter
            )
        }

        initializer {
            JadwalViewModel(
                ayoSehatApp().jadwalApp.repositoryJadwal
            )
        }

        initializer {
            HomeJadwalViewModel(
                ayoSehatApp().jadwalApp.repositoryJadwal
            )
        }

        initializer {
            DetailJadwalViewModel(
                createSavedStateHandle(),
                ayoSehatApp().jadwalApp.repositoryJadwal
            )
        }

        initializer {
            UpdateJadwalViewModel(
                createSavedStateHandle(),
                ayoSehatApp().jadwalApp.repositoryJadwal
            )
        }









    }
}

fun CreationExtras.ayoSehatApp(): AyoSehatApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as AyoSehatApp)
