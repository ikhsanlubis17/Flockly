package com.example.front_end.Navigasi.Nav_Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.front_end.Navigasi.PENDATAAN_ROUTE
import com.example.front_end.Navigasi.Screen
import com.example.front_end.Screens.BiayaOperasional
import com.example.front_end.Screens.DaftarTernak
import com.example.front_end.Screens.HasilProduksi
import com.example.front_end.Screens.KonsumsiPakan
import com.example.front_end.Screens.Pendapatan
import com.example.front_end.Screens.Pendataan

fun NavGraphBuilder.pendataanNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Pendataan.route,
        route = PENDATAAN_ROUTE
    ) {
        composable(route = Screen.Pendataan.route) {Pendataan(navController = navController) }
        composable(route = Screen.DaftarTernak.route){ DaftarTernak(navController = navController) }
        composable(route = Screen.HasilProduksi.route){ HasilProduksi(navController = navController) }
        composable(route = Screen.KonsumsiPakan.route){ KonsumsiPakan(navController = navController) }
        composable(route = Screen.Pendapatan.route){ Pendapatan(navController = navController) }
        composable(route = Screen.BiayaOperasional.route){ BiayaOperasional(navController = navController) }
    }
}