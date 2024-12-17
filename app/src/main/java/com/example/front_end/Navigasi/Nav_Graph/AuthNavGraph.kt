package com.example.front_end.Navigasi.Nav_Graph

import android.annotation.SuppressLint
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.front_end.Navigasi.AUTHENTICATION_ROUTE
import com.example.front_end.Navigasi.Screen
import com.example.front_end.Screens.DaftarScreen
import com.example.front_end.Screens.LoginScreen
import com.example.front_end.Screens.Verifikasi

@SuppressLint("NewApi")
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.LoginScreen.route,
        route = AUTHENTICATION_ROUTE
    ){
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.DaftarScreen.route){ DaftarScreen(navController = navController) }
        composable(route = Screen.Verifikasi.route){ Verifikasi(navController = navController) }

    }
}