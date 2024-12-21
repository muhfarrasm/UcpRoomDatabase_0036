package com.example.ucp2roomdatabase_0036.ui.viewmodel.jadwal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2roomdatabase_0036.data.entity.Jadwal
import com.example.ucp2roomdatabase_0036.repository.RepositoryJadwal
import kotlinx.coroutines.launch

//data class variable yang menyimpan
//data input form
data class JadwalEvent(
    val id : String ="",
    val NamaDokter: String ="",
    val NamaPasien : String ="",
    val NoHp : String ="",
    val TglKonsultasi : String ="",
    val Status : String =""
)

//Menyimpan input form ke dalam entity
fun JadwalEvent.toJadwalEntity(): Jadwal = Jadwal(

    id = id,
    NamaDokter = NamaDokter,
    NamaPasien = NamaPasien,
    NoHp = NoHp,
    TglKonsultasi = TglKonsultasi,
    Status = Status
)


data class FormErrorState(
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
    val jadwalEvent: JadwalEvent = JadwalEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)

class JadwalViewModel(
    private val repositoryJadwal: RepositoryJadwal
) : ViewModel(){
    var uiState by mutableStateOf(JadwalUiState())
    fun updateState (jadwalEvent: JadwalEvent){
        uiState = uiState.copy(
            jadwalEvent = jadwalEvent,
        )
    }

    private fun validateFields(): Boolean{
        val event = uiState.jadwalEvent
        val errorState = FormErrorState(
            id = if (event.id.isNotEmpty()) null else "ID tidak boleh kosong",
            NamaDokter = if (event.NamaDokter.isNotEmpty()) null else "Nama Dokter tidak boleh kosong",
            NamaPasien = if (event.NamaPasien.isNotEmpty()) null else "Nama Pasien tidak boleh kosong",
            NoHp = if (event.NoHp.isNotEmpty()) null else "NoHp tidak boleh kosong",
            TglKonsultasi = if (event.TglKonsultasi.isNotEmpty()) null else "TglKonsultasi tidak boleh kosong",
            Status = if (event.Status.isNotEmpty()) null else "Status tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.jadwalEvent

        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryJadwal.InsertJadwal(currentEvent.toJadwalEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        jadwalEvent = JadwalEvent(),
                        isEntryValid = FormErrorState()
                    )
                }catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )

                }
            }
        }
        else{
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. periksa kembali data anda"
            )
        }
    }
    fun resetSnackBarMassage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}