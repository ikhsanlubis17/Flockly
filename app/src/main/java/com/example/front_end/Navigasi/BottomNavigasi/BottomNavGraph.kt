package com.example.front_end.Navigasi.BottomNavigasi

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.front_end.R
import com.example.front_end.Screens.HomePage
import com.example.front_end.Screens.ProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomePage(navController = navController)
        }
        composable(route = BottomBarScreen.Statistik.route) {
            AnalisisData()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(
                navController = navController,
                profileImageResource = R.drawable.fotofrans,
                editIconResource = R.drawable.edit
            )
        }
    }
}

@Composable
fun AnalisisData() {
    TODO("Not yet implemented")
}
