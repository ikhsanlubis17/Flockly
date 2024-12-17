package com.example.front_end.Navigasi.Nav_Graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.front_end.Navigasi.ROOT_ROUTE
import com.example.front_end.Navigasi.SPLASH_ROUTE


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SPLASH_ROUTE,
        route = ROOT_ROUTE
    ) {
        splashNavGraph(navController = navController)
        authNavGraph(navController = navController)
        homesNavGraph(navController = navController)
        pendataanNavGraph(navController = navController)
        penjadwalanNavGraph(navController = navController)
        kandangNavGraph(navController = navController)
    }
}



