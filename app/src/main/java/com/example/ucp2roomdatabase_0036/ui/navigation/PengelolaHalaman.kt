package com.example.ucp2roomdatabase_0036.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ucp2roomdatabase_0036.ui.view.dokter.HomeDokterView
import com.example.ucp2roomdatabase_0036.ui.view.dokter.InsertDokterView
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2roomdatabase_0036.ui.view.jadwal.DetailJadwalView
import com.example.ucp2roomdatabase_0036.ui.view.jadwal.HomeJadwalView
import com.example.ucp2roomdatabase_0036.ui.view.jadwal.InsertJadwalView
import com.example.ucp2roomdatabase_0036.ui.view.jadwal.UpdateJadwalView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = DestinasiHomeDokter.route) {
        composable(
            route = DestinasiHomeDokter.route
        ) {
            HomeDokterView(
                onAddDktr = {
                    navController.navigate(DestinasiInsertDokter.route)
                },
                onSeeJadwal = {
                    navController.navigate(DestinasiHomeJadwal.route)
                },

                modifier = modifier,



            )
        }

        composable(
            route = DestinasiInsertDokter.route
        ) {
            InsertDokterView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }

        composable(
            route = DestinasiHomeJadwal.route
        ) {
            HomeJadwalView(
                onAddJdwl = {
                    navController.navigate(DestinasiInsertJadwal.route)
                },
                onSeeDokter ={
                    navController.navigate(DestinasiHomeDokter.route)
                },
                onDetailClick = { id ->
                    navController.navigate("${DestinasiDetailJadwal.route}/$id")
                    println("PengelolaHalaman: id = $id")
                },

                modifier = modifier,

            )
        }

        composable(
            route = DestinasiInsertJadwal.route
        ) {
            InsertJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            DestinasiDetailJadwal.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailJadwal.idJadwal) {
                    type = NavType.StringType
                }
            )
        ) {
            val idJadwal = it.arguments?.getString(DestinasiDetailJadwal.idJadwal)
            idJadwal?.let { idJadwal ->
                DetailJadwalView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateJadwal.route}/$it")
                    },

                    onDeleteClick = {
                        navController.popBackStack()
                    },
                    modifier = modifier
                )
            }
        }

        composable(
            DestinasiUpdateJadwal.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateJadwal.idJadwal){
                    type = NavType.StringType
                }
            )
        ){
            UpdateJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

    }
}