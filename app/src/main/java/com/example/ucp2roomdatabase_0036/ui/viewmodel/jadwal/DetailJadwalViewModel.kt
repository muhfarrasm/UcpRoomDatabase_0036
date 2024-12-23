package com.example.ucp2roomdatabase_0036.ui.viewmodel.jadwal



import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2roomdatabase_0036.data.entity.Jadwal
import com.example.ucp2roomdatabase_0036.repository.RepositoryJadwal
import com.example.ucp2roomdatabase_0036.ui.navigation.DestinasiDetailJadwal
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailJdlViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryJadwal: RepositoryJadwal
) : ViewModel(){
    private val _idjdl: String = checkNotNull(savedStateHandle[DestinasiDetailJadwal.idJadwal])
    val detailUiState: StateFlow<DetailUiState> = repositoryJadwal.getJadwalByid(_idjdl)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetaiJadwalUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?:"Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailUiState(
                isLoading = true,
            )
        )

    fun deletejadwal(){
        detailUiState.value.detailUiEvent.toJadwalEntity().let {
            viewModelScope.launch {
                repositoryJadwal.DeleteJadwal(it)
            }
        }
    }

}

data class DetailUiState(
    val detailUiEvent: JadwalEvent = JadwalEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == JadwalEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != JadwalEvent()
}

// Data class untuk menampung data yang akan ditampilkan di Ui

//memindahkan data dari Entity ke ui
fun Jadwal.toDetaiJadwalUiEvent(): JadwalEvent{
    return JadwalEvent(
        id = id,
        NamaPasien = NamaPasien,
        NamaDokter = NamaDokter,
        TglKonsultasi = TglKonsultasi,
        NoHp = NoHp,
        Status = Status

    )
}