package com.example.ucp2roomdatabase_0036.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import com.example.ucp2roomdatabase_0036.data.entity.Jadwal
import com.example.ucp2roomdatabase_0036.repository.RepositoryJadwal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class JadwalViewModel(
    private val repositoryJadwal: RepositoryJadwal
) : ViewModel() {

    var uiState by mutableStateOf(JadwalUiState())
    val listDokter: Flow<List<Dokter>> = repositoryJadwal.getAllNamaDokter()

    fun updateState(jadwalEvent : JadwalEvent) {
        uiState = uiState.copy(jadwalEvent = jadwalEvent)
    }

    private fun validateFields(): Boolean {
        val event = uiState.jadwalEvent
        val errorState = FormErrorStateJdwl(
            id = if (event.id.isNotEmpty()) null else "ID tidak boleh kosong",
            NamaDokter = if (event.NamaDokter.isNotEmpty()) null else "Nama Dokter tidak boleh kosong",
            NamaPasien = if (event.NamaPasien.isNotEmpty()) null else "Nama Pasien tidak boleh kosong",
            NoHp = if (event.NoHp.isNotEmpty()) null else "NoHp harus berupa angka dan dimulai dengan 08",
            TglKonsultasi = if (event.TglKonsultasi.isNotEmpty()) null else "Tanggal Konsultasi tidak boleh kosong",
            Status = if (event.Status.isNotEmpty()) null else "Status tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.jadwalEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryJadwal.InsertJadwal(currentEvent.toJadwalEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        jadwalEvent = JadwalEvent(),
                        isEntryValid = FormErrorStateJdwl()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }

    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }


}
//data class variable yang menyimpan
//data input form
data class JadwalEvent(
    val id : String ="",
    val TglKonsultasi : String ="",
    val NamaDokter: String ="",
    val NamaPasien : String ="",
    val NoHp : String ="",
    val Status : String =""
)

//Menyimpan input form ke dalam entity
fun JadwalEvent.toJadwalEntity(): Jadwal = Jadwal(

    id = id,
    TglKonsultasi = TglKonsultasi,
    NamaDokter = NamaDokter,
    NamaPasien = NamaPasien,
    NoHp = NoHp,
    Status = Status
)


data class FormErrorStateJdwl(
    val id: String? = null,
    val NamaDokter: String?= null,
    val NamaPasien: String? = null,
    val NoHp: String? = null,
    val TglKonsultasi: String? = null,
    val Status: String? = null
) {
    fun isValid(): Boolean {
        return id == null && NamaDokter == null && NamaPasien == null && NoHp == null
                && TglKonsultasi == null && Status == null
    }
}

data class JadwalUiState(
    val jadwalEvent: JadwalEvent= JadwalEvent(),
    val isEntryValid: FormErrorStateJdwl = FormErrorStateJdwl(),
    val snackBarMessage: String? = null
)

