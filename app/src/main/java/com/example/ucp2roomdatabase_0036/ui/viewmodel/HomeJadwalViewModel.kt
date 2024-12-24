package com.example.ucp2roomdatabase_0036.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2roomdatabase_0036.data.entity.Jadwal
import com.example.ucp2roomdatabase_0036.repository.RepositoryJadwal

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeJadwalViewModel(
    private val repositoryJadwal: RepositoryJadwal
) : ViewModel() {

    val homeUiState: StateFlow<HomeJadwalUiState> = createStateFlow()

    private fun createStateFlow(): StateFlow<HomeJadwalUiState> {
        return repositoryJadwal.getAllJadwal()
            .filterNotNull()
            .map { jadwalList ->
                HomeJadwalUiState(
                    listJadwal = jadwalList.filterNotNull(), // Validasi tambahan
                    isLoading = false
                )
            }
            .onStart {
                emit(HomeJadwalUiState(isLoading = true))
            }
            .catch { exception ->
                val errorMessage = exception.localizedMessage ?: "Terjadi Kesalahan"
                emit(
                    HomeJadwalUiState(
                        isLoading = false,
                        isError = true,
                        errorMessages = errorMessage
                    )
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomeJadwalUiState(isLoading = true)
            )
    }
}

data class HomeJadwalUiState(
    val listJadwal: List<Jadwal> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessages: String = ""
)