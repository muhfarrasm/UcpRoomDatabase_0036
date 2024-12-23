package com.example.ucp2roomdatabase_0036.ui.viewmodel.jadwal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2roomdatabase_0036.data.entity.Jadwal
import com.example.ucp2roomdatabase_0036.repository.RepositoryJadwal
import com.example.ucp2roomdatabase_0036.ui.navigation.DestinasiUpdateJadwal
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateJadwalViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryJadwal: RepositoryJadwal
) : ViewModel() {
    var updateUIState by mutableStateOf(JadwalUiState())
        private set

    private val _id: String = checkNotNull(savedStateHandle[DestinasiUpdateJadwal.idJadwal])
    init {
        viewModelScope.launch {
            updateUIState = repositoryJadwal.getJadwalByid(_id)
                .filterNotNull()
                .first()
                .toUIStateJadwal()
        }
    }

    fun updateState(jadwalEvent: JadwalEvent) {
        updateUIState = updateUIState.copy(
            jadwalEvent = jadwalEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = updateUIState.jadwalEvent
        val errorState = FormErrorStateJdwl(
            id = if (event.id.isNotEmpty()) null else "NIM Tidak Boleh Kosong",
            NamaPasien = if (event.NamaPasien.isNotEmpty()) null else "Nama Tidak Boleh Kosong",
            NamaDokter = if (event.NamaDokter.isNotEmpty()) null else "Jenis Kelamin Tidak Boleh Kosong",
            TglKonsultasi = if (event.TglKonsultasi.isNotEmpty()) null else "Alamat Tidak Boleh Kosong",
            NoHp = if (event.NoHp.isNotEmpty()) null else "Kelas Tidak Boleh Kosong",
            Status = if (event.Status.isNotEmpty()) null else "Angkatan Tidak Boleh Kosong",
        )

        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUIState.jadwalEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryJadwal.UpdateJadwal(currentEvent.toJadwalEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data Berhasil Diupdate",
                        jadwalEvent = JadwalEvent(),
                        isEntryValid = FormErrorStateJdwl(),
                    )
                    println("snackBarMessage Diatur: ${updateUIState.snackBarMessage}")
                } catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data Gagal Diupdate"
                    )
                }
            }
        } else  {
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data Gagal Diupdate"
            )
        }
    }
    fun resetSnackBarMessage() {
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }
}

fun Jadwal.toUIStateJadwal(): JadwalUiState = JadwalUiState(
    jadwalEvent = this.toDetaiJadwalUiEvent(),
)
