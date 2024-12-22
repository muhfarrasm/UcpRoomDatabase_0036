package com.example.ucp2roomdatabase_0036.ui.view.dokter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2roomdatabase_0036.ui.customwidget.TopAppBar
import com.example.ucp2roomdatabase_0036.ui.navigation.AlamatNavigasi
import com.example.ucp2roomdatabase_0036.ui.viewmodel.PenyediaViewModel

import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.DokterEvent
import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.DokterUiState
import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.DokterViewModel
import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.FormErrorState
import kotlinx.coroutines.launch

@Composable
fun FormDokter(
    dokterEvent: DokterEvent = DokterEvent(),
    onValueChange: (DokterEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier : Modifier = Modifier
){
    val Spesialis = listOf("Anak", "Umum", "Mata")


    Column (
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.Id,
            onValueChange = {
                onValueChange(dokterEvent.copy(Id =it))
            },
            label = { Text("Id") },

            isError = errorState.Id != null,
            placeholder = { Text("Masukkan ID") },
        )
        Text(
            text = errorState.Id ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.NamaDokter,
            onValueChange = {
                onValueChange(dokterEvent.copy(NamaDokter =it))
            },
            label = { Text("Nama") },
            isError = errorState.NamaDokter != null,
            placeholder = { Text("Masukkan Nama") },
        )

        Text(
            text = errorState.NamaDokter ?: "",
            color = Color.Red
        )

        Text(text = "Spesialis")
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            Spesialis.forEach { Spl ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = dokterEvent.Spesialis == Spl,
                        onClick = {
                            onValueChange(dokterEvent.copy(Spesialis = Spl))
                        }
                    )
                    Text(
                        text = Spl,
                    )

                }
            }

        }
        Text(
            text = errorState.Spesialis ?:"",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.Klinik,
            onValueChange = {
                onValueChange(dokterEvent.copy(Klinik =it))
            },
            label = { Text("Klinik") },
            isError = errorState.Klinik != null,
            placeholder = { Text("Masukkan Klinik") },
        )

        Text(
            text = errorState.Klinik ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.NoHpDokter,
            onValueChange = {
                onValueChange(dokterEvent.copy(NoHpDokter = it))
            },
            label = { Text("NoHpDokter") },
            isError = errorState.NoHpDokter != null,
            placeholder = { Text("Masukkan NoHpDokter") },
        )

        Text(
            text = errorState.NoHpDokter ?: "",
            color = Color.Red
        )


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dokterEvent.JamKerja,
            onValueChange = {
                onValueChange(dokterEvent.copy(JamKerja = it))
            },
            label = { Text("JamKerja") },
            isError = errorState.JamKerja != null,
            placeholder = { Text("Masukkan JamKerja") },
        )

        Text(
            text = errorState.JamKerja ?: "",
            color = Color.Red
        )
    }
}

@Composable
fun InsertBodyDokter(
    modifier: Modifier = Modifier,
    onValueChange: (DokterEvent) -> Unit,
    uiState: DokterUiState,
    onClick: () -> Unit
){
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormDokter(
            dokterEvent = uiState.dokterEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Save")
        }
    }
}



@Composable
fun InsertDokterView(
    onBack: ()-> Unit,
    onNavigate:() -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DokterViewModel = viewModel(factory = PenyediaViewModel.Factory)
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
                judul = "Tambah Dokter"

            )
            InsertBodyDokter(
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