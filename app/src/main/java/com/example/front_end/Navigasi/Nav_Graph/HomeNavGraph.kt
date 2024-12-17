package com.example.front_end.Navigasi.Nav_Graph

import android.annotation.SuppressLint
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.front_end.Navigasi.HOME_ROUTE
import com.example.front_end.Navigasi.Screen
import com.example.front_end.R
import com.example.front_end.Screens.History
import com.example.front_end.Screens.HomePage
import com.example.front_end.Screens.KandangdanRincian
import com.example.front_end.Screens.Monitoring
import com.example.front_end.Screens.Notifikasi
import com.example.front_end.Screens.Pendataan
import com.example.front_end.Screens.Penjadwalan

@SuppressLint("NewApi")
fun NavGraphBuilder.homesNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.HomePage.route,
        route = HOME_ROUTE
    ){
        composable(route = Screen.HomePage.route){ HomePage(navController = navController) }
        composable(route = Screen.Pendataan.route){ Pendataan(navController = navController) }
        composable(route = Screen.KandangdanRincian.route){ KandangdanRincian(
                navController = navController,
                backIcon = R.drawable.back1,
                searchIcon = R.drawable.search,
            addIcon = android.R.drawable.ic_input_add,
                kandangAyamImage = R.drawable.fotokandang,
                kandangBebekImage = R.drawable.fotokandang,
                kandangPuyuhImage = R.drawable.fotokandang
            )
        }
        composable(route = Screen.Penjadwalan.route){ Penjadwalan(navController = navController) }
        composable(route = Screen.MonitoringProduktivitas.route){ Monitoring(navController = navController) }
        composable(route = Screen.Notifikasi.route){ Notifikasi(navController = navController) }
        composable(route = Screen.History.route){ History(navController = navController) }
    }

}