package com.example.ucp2roomdatabase_0036.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2roomdatabase_0036.AyoSehatApp
import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.DokterViewModel
import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.HomeDokterViewModel

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
    }
}

fun CreationExtras.ayoSehatApp(): AyoSehatApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as AyoSehatApp)