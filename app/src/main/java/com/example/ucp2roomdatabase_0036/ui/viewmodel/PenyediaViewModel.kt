package com.example.ucp2roomdatabase_0036.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2roomdatabase_0036.AyoSehatApp
import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.DokterViewModel
import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.HomeDokterViewModel
import com.example.ucp2roomdatabase_0036.ui.viewmodel.jadwal.DetailJadwalViewModel
import com.example.ucp2roomdatabase_0036.ui.viewmodel.jadwal.HomeJadwalViewModel
import com.example.ucp2roomdatabase_0036.ui.viewmodel.jadwal.JadwalViewModel
import com.example.ucp2roomdatabase_0036.ui.viewmodel.jadwal.UpdateJadwalViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            DokterViewModel(
                ayoSehatApp().containerApp.repositoryDokter
            )
        }

        initializer {
            HomeDokterViewModel(
                ayoSehatApp().containerApp.repositoryDokter
            )
        }

        initializer {
            JadwalViewModel(
                ayoSehatApp().containerApp.repositoryJadwal
            )
        }

        initializer {
            HomeJadwalViewModel(
                ayoSehatApp().containerApp.repositoryJadwal
            )
        }

        initializer {
            DetailJadwalViewModel(
                createSavedStateHandle(),
                ayoSehatApp().containerApp.repositoryJadwal
            )
        }

        initializer {
            UpdateJadwalViewModel(
                createSavedStateHandle(),
                ayoSehatApp().containerApp.repositoryJadwal
            )
        }

    }
}

fun CreationExtras.ayoSehatApp(): AyoSehatApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as AyoSehatApp)