package com.example.front_end.Navigasi.Nav_Graph

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.front_end.Navigasi.PENJADWALAN_ROUTE
import com.example.front_end.Navigasi.Screen
import com.example.front_end.Screens.Penjadwalan
import com.example.front_end.Screens.Riwayat
import com.example.front_end.Screens.Selesai
import com.example.front_end.Screens.TambahJadwal
import com.example.front_end.Screens.Task

fun NavGraphBuilder.penjadwalanNavGraph(
    navController: NavHostController
) {
    val tasks = listOf(
        Task("Pakan Ayam Petelur", "Selesai", "Sep 15, 2023", "", Color(0xFF5F4A43)),
        Task("Vaksin Ayam Pedaging", "Belum Selesai", "Sep 14, 2023", "", Color(0xFFF7F3EA)),
        Task("Pembersihan Kandang Bebek", "Belum Selesai", "Sep 13, 2023", "", Color(0xFFF7F3EA)),
        Task("Pakan Puyuh", "Selesai", "Sep 12, 2023", "", Color(0xFF5F4A43))
    )

    navigation(
        startDestination = Screen.Penjadwalan.route,
        route = PENJADWALAN_ROUTE
    ) {
        composable(route = Screen.Penjadwalan.route) { Penjadwalan(navController = navController) }
        composable(route = Screen.TambahJadwal.route) { TambahJadwal(navController = navController) }
        composable(route = Screen.Selesai.route) { Selesai(navController = navController) }
        composable(route = Screen.Riwayat.route) { Riwayat(navController = navController, tasks = tasks) }
    }
}
