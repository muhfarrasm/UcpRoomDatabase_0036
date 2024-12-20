package com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import com.example.ucp2roomdatabase_0036.repository.RepositoryDokter
import kotlinx.coroutines.launch

//data class variable yang menyimpan
//data input form
data class DokterEvent(
    val Id : String ="",
    val NamaDokter : String ="",
    val Spesialis : String ="",
    val Klinik : String ="",
    val NoHpDokter : String ="",
    val JamKerja : String =""
)

//Menyimpan input form ke dalam entity
fun DokterEvent.toDokterEntity():Dokter = Dokter(

    Id = Id,
    NamaDokter = NamaDokter,
    Spesialis = Spesialis,
    Klinik = Klinik,
    NoHpDokter = NoHpDokter,
    JamKerja = JamKerja
)


data class FormErrorState(
    val Id: String? = null,
    val NamaDokter: String?= null,
    val Spesialis: String? = null,
    val Klinik: String? = null,
    val NoHpDokter: String? = null,
    val JamKerja: String? = null
) {
    fun isValid(): Boolean {
        return Id == null && NamaDokter == null && Spesialis == null && Klinik == null
                && NoHpDokter == null && JamKerja == null
    }
}

data class DokterUiState(
    val dokterEvent: DokterEvent = DokterEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)

class DokterViewModel (
    private val repositoryDokter: RepositoryDokter
) : ViewModel(){
    var uiState by mutableStateOf(DokterUiState())
    fun updateState (dokterEvent: DokterEvent){
        uiState = uiState.copy(
            dokterEvent = dokterEvent,
        )
    }

    private fun validateFields(): Boolean{
        val event = uiState.dokterEvent
        val errorState = FormErrorState(
            Id = if (event.Id.isNotEmpty()) null else "ID tidak boleh kosong",
            NamaDokter = if (event.NamaDokter.isNotEmpty()) null else "Nama tidak boleh kosong",
            Spesialis = if (event.Spesialis.isNotEmpty()) null else "Spesialis tidak boleh kosong",
            Klinik = if (event.Klinik.isNotEmpty()) null else "Klinik tidak boleh kosong",
            NoHpDokter = if (event.NoHpDokter.isNotEmpty()) null else "No HP tidak boleh kosong",
            JamKerja = if (event.JamKerja.isNotEmpty()) null else "Jam Kerja tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.dokterEvent

        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryDokter.InsertDokter(currentEvent.toDokterEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        dokterEvent = DokterEvent(),
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