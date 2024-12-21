package com.example.ucp2roomdatabase_0036.ui.view.jadwal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2roomdatabase_0036.ui.customwidget.DynamicSelectedTextField
import com.example.ucp2roomdatabase_0036.ui.customwidget.TopAppBar
import com.example.ucp2roomdatabase_0036.ui.view.dokter.InsertBodyDokter
import com.example.ucp2roomdatabase_0036.ui.viewmodel.PenyediaViewModel
import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.DokterViewModel
import com.example.ucp2roomdatabase_0036.ui.viewmodel.jadwal.FormErrorStateJdwl

import com.example.ucp2roomdatabase_0036.ui.viewmodel.jadwal.JadwalEvent
import com.example.ucp2roomdatabase_0036.ui.viewmodel.jadwal.JadwalUiState
import com.example.ucp2roomdatabase_0036.ui.viewmodel.jadwal.JadwalViewModel
import kotlinx.coroutines.launch

@Composable
fun FormJadwal(
    jadwalEvent: JadwalEvent = JadwalEvent(),
    viewModel: JadwalViewModel = viewModel(),
    onValueChange: (JadwalEvent) -> Unit,
    errorState: FormErrorStateJdwl = FormErrorStateJdwl(),
    modifier: Modifier = Modifier
){
    var chosenDropdown by remember { mutableStateOf("") }

    var PilihDokter by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jadwalEvent.NamaPasien,
            onValueChange = {
                onValueChange(jadwalEvent.copy(NamaPasien = it))
            },
            label = {
                Text("Nama Pasien")
            },
            isError = errorState.NamaPasien != null,
            placeholder = {
                Text("Masukkan Nama Pasien")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(10.dp)
        )
        Text(
            text = errorState.NamaPasien ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jadwalEvent.TglKonsultasi,
            onValueChange = {
                onValueChange(jadwalEvent.copy(TglKonsultasi = it))
            },
            label = {
                Text("Tanggal Konsultasi")
            },
            isError = errorState.TglKonsultasi != null,
            placeholder = {
                Text("Masukkan Tanggal Konsultasi")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(10.dp)
        )
        Text(
            text = errorState.TglKonsultasi ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mengambil Data Nama Dokter dari ViewModel
        LaunchedEffect (Unit) {
            viewModel.listDokter.collect { dokterList ->
                PilihDokter = dokterList.map { it.NamaDokter }
            }
        }

        DynamicSelectedTextField(
            selectedValue = chosenDropdown,
            options = PilihDokter,
            label = "Pilih Nama Dokter",
            onValueChangedEvent = {
                onValueChange(jadwalEvent.copy(NamaDokter = it))
                chosenDropdown = it
            }
        )
        Text(
            text = errorState.NamaDokter ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jadwalEvent.NoHp,
            onValueChange = {
                onValueChange(jadwalEvent.copy(NoHp = it))
            },
            label = {
                Text("No HP")
            },
            isError = errorState.NoHp != null,
            placeholder = {
                Text("Masukkan No HP")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(10.dp)
        )
        Text(
            text = errorState.NoHp ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jadwalEvent.Status,
            onValueChange = {
                onValueChange(jadwalEvent.copy(Status = it))
            },
            label = {
                Text("Status Penanganan")
            },
            isError = errorState.Status != null,
            placeholder = {
                Text("Masukkan Status Penanganan")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(10.dp)
        )
        Text(
            text = errorState.Status ?: "",
            color = Color.Red
        )
    }
}

@Composable
@Preview
fun InsertBodyJdlPreview() {
    InsertBodyJadwal(
        uiState = JadwalUiState(),
        onValueChange = {},
        onClick = {}
    )
}
@Composable
fun InsertBodyJadwal(
    modifier: Modifier = Modifier,
    onValueChange: (JadwalEvent) -> Unit,
    uiState: JadwalUiState,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormJadwal(
            jadwalEvent = uiState.jadwalEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonColors(
                containerColor = Color(0xFF00AAEC),
                contentColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = Color(0xFF00AAEC)
            )
        ) {
            Text("Simpan")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun InsertJadwalViewPreview() {
    InsertJadwalView(onBack = {}, onNavigate = {})
}
@Composable
fun InsertJadwalView(
    onBack: ()-> Unit,
    onNavigate:() -> Unit,
    modifier: Modifier = Modifier,
    viewModel: JadwalViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState // Ambil UI state dari View Model
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(message)
                viewModel.resetSnackBarMassage()
            }
        }
    }
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ){padding  ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Jadwal"

            )
            InsertBodyJadwal(
                uiState = uiState,
                onValueChange = {
                        updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData()
                    }
                    onNavigate
                }
            )
        }

    }
}
