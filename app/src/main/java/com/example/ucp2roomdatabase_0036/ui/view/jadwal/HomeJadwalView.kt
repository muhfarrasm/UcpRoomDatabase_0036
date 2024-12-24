package com.example.ucp2roomdatabase_0036.ui.view.jadwal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2roomdatabase_0036.R
import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import com.example.ucp2roomdatabase_0036.data.entity.Jadwal
import com.example.ucp2roomdatabase_0036.ui.customwidget.TopAppBar
import com.example.ucp2roomdatabase_0036.ui.viewmodel.HomeJadwalUiState
import com.example.ucp2roomdatabase_0036.ui.viewmodel.HomeJadwalViewModel
import com.example.ucp2roomdatabase_0036.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api :: class)
@Composable
fun CardJadwal(
    Jdwl: Jadwal,
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
                    text = Jdwl.NamaPasien,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = Jdwl.NamaDokter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Filled.Home, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = Jdwl.TglKonsultasi,
                    fontWeight = FontWeight.Bold,
                )
            }


        }

    }

}

@Composable
fun ListJadwal(
    listJdwl: List<Jadwal>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
){
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listJdwl,
            itemContent = { jdwl ->
                CardJadwal(
                    Jdwl = jdwl,
                    onClick = { onClick(jdwl.id)}
                )
            }
        )
    }

}

@Composable
fun BodyHomeJadwalView(
    homeJadwalUiState: HomeJadwalUiState,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    when {
        homeJadwalUiState.isLoading -> {
            // Menampilkan Indikator Loading
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeJadwalUiState.isError -> {
            // Menampilkan Snackbar jika terjadi kesalahan
            LaunchedEffect(homeJadwalUiState.errorMessages) {
                homeJadwalUiState.errorMessages.let { message ->
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(message) // Tampilkan Snackbar
                    }
                }
            }
        }

        homeJadwalUiState.listJadwal.isEmpty() -> {
            // Menampilkan pesan jika daftar dokter kosong
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak Ada Data Jadwal",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            // Menampilkan daftar dokter jika tidak ada kesalahan
            ListJadwal(
                listJdwl = homeJadwalUiState.listJadwal,
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
fun Header(
    namaApp: String,
    ID: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF6200EE),
                        Color(0xFF3700B3)
                    )
                )
            )
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(Color.White.copy(alpha = 0.3f))
            ) {
                Image(
                    painter = painterResource(ID),
                    contentDescription = "Profil Perusahaan",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(35.dp))
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = namaApp,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "ikon",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Ayo Sehatkan Gen Z",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }
}



@Composable
fun HomeJadwalView(
    viewModel: HomeJadwalViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onSeeDokter: () -> Unit = { },
    onSeeJadwal: () -> Unit = { },
    onAddJdwl: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier


) {
    Scaffold (
        modifier = modifier,
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(21.dp))
                Header(
                    namaApp = "Ayo Sehat",
                    ID = R.drawable.rs
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = onSeeDokter,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Daftar Dokter",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Button(
                        onClick = onAddJdwl,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),

                        ) {
                        Text(
                            text = "Tambah Jadwal",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))

                TopAppBar(
                    judul = "Daftar Jadwal",
                    showBackButton = false,
                    onBack = { }
                )
            }
        },
    ) {
            innerPadding ->
        val homeJadwalUiState by viewModel.homeUiState.collectAsState()

        BodyHomeJadwalView(
            homeJadwalUiState = homeJadwalUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}