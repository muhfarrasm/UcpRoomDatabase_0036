package com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import com.example.ucp2roomdatabase_0036.repository.RepositoryDokter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeDokterViewModel(
    private val repositoryDokter: RepositoryDokter
) : ViewModel(){
    val homeUiState: StateFlow<HomeDokterUiState> = repositoryDokter.getAllDokter()
        .filterNotNull()
        .map {
            HomeDokterUiState(
                listDktr = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeDokterUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeDokterUiState(
                    isLoading = false,
                    isError = true,
                    errorMessages = it.message ?:"Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeDokterUiState(
                isLoading = true,
            )
        )

}

data class HomeDokterUiState(
    val listDktr: List<Dokter> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessages: String = ""
)