package com.example.ucp2roomdatabase_0036.ui.view.dokter

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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2roomdatabase_0036.R
import com.example.ucp2roomdatabase_0036.data.entity.Dokter
import com.example.ucp2roomdatabase_0036.ui.customwidget.TopAppBar
import com.example.ucp2roomdatabase_0036.ui.viewmodel.PenyediaViewModel
import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.HomeDokterUiState
import com.example.ucp2roomdatabase_0036.ui.viewmodel.dokter.HomeDokterViewModel
import kotlinx.coroutines.launch



fun Color(Spesialis : String): Color{
    return when (Spesialis) {
        "Anak" -> Color.Blue
        "Umum" -> Color.Green
        "Mata" -> Color.Red
        else -> Color.Gray

    }
}
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
                    text = "Spesialis: ${dktr.Spesialis}",
                    color = Color(dktr.Spesialis),
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
                        Color(0xFF6700ff),
                        Color(0xFF6700ff)
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
                homeDokterUiState.errorMessages.let { message ->
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
    onSeeDokter: () -> Unit = { },
    onSeeJadwal: () -> Unit = { },
    onAddJdwl: () -> Unit = { },
    onAddDktr: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier,

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
                        onClick = onAddDktr,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),

                    ) {
                        Text(
                            text = "Tambah Dokter",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }


                    Button(
                        onClick = onSeeJadwal,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Jadwal",
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                TopAppBar(
                    judul = "Daftar Dokter",
                    showBackButton = false,
                    onBack = { }
                )
            }
        },

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


