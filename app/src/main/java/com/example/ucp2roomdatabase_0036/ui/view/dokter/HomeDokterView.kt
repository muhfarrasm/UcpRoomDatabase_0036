package com.example.ucp2roomdatabase_0036.ui.view.dokter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import com.example.ucp2roomdatabase_0036.ui.customwidget.TopAppBar
import com.example.ucp2roomdatabase_0036.ui.viewmodel.PenyediaViewModel
import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.HomeDokterUiState
import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.HomeDokterViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api :: class)
@Composable
fun CardDokter(
    dktr: Dokter,
    modifier: Modifier = Modifier,
    onClick : () -> Unit ={ }
){
    Card (

        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick
    ){
        Column (
            modifier = Modifier.padding(8.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dktr.NamaDokter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dktr.Spesialis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Filled.Home, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dktr.Klinik,
                    fontWeight = FontWeight.Bold,
                )
            }


        }

    }

}

@Composable
fun ListDokter(
    listdktr: List<Dokter>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
){
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listdktr,
            itemContent = { dktr ->
                CardDokter(
                    dktr = dktr,
                    onClick = { onClick(dktr.Id) }
                )
            }
        )
    }

}

@Composable
fun BodyHomeDktrView(
    homeDokterUiState: HomeDokterUiState,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    when {
        homeDokterUiState.isLoading -> {
            // Menampilkan Indikator Loading
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeDokterUiState.isError -> {
            // Menampilkan Snackbar jika terjadi kesalahan
            LaunchedEffect(homeDokterUiState.errorMessages) {
                homeDokterUiState.errorMessages?.let { message ->
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(message) // Tampilkan Snackbar
                    }
                }
            }
        }

        homeDokterUiState.listDktr.isEmpty() -> {
            // Menampilkan pesan jika daftar dokter kosong
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak Ada Data Dokter",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            // Menampilkan daftar dokter jika tidak ada kesalahan
            ListDokter(
                listdktr = homeDokterUiState.listDktr,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun HomeDokterView(
    viewModel: HomeDokterViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddDktr: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
    Scaffold (
        modifier = modifier,
        topBar = {
            TopAppBar(
                judul = "Daftar Dokter",
                showBackButton = false,
                onBack = {},
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddDktr,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Mahasiswa"
                )
            }
        }
    ) {
            innerPadding ->
        val homeDokterUiState by viewModel.homeUiState.collectAsState()

        BodyHomeDktrView(
            homeDokterUiState = homeDokterUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}


