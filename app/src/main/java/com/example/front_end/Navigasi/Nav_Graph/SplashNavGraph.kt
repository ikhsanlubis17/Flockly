package com.example.front_end.Navigasi.Nav_Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.front_end.Navigasi.SPLASH_ROUTE
import com.example.front_end.Navigasi.Screen
import com.example.front_end.Screens.LandingPage
import com.example.front_end.Screens.SplashScreen1
import com.example.front_end.Screens.SplashScreen2
import com.example.front_end.Screens.SplashScreen3
import com.example.front_end.Screens.SplashScreen4

fun NavGraphBuilder.splashNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.LandingPage.route,
        route = SPLASH_ROUTE
    ){
        composable(
            route = Screen.LandingPage.route
        ) {
            LandingPage(navController = navController)
        }

        composable(
            route = Screen.SplashScreen1.route
        ) {
            SplashScreen1(navController = navController)
        }

        composable(
            route = Screen.SplashScreen2.route
        ) {
            SplashScreen2(navController = navController)
        }

        composable(
            route = Screen.SplashScreen3.route
        ) {
            SplashScreen3(navController = navController)
        }

        composable(
            route = Screen.SplashScreen4.route
        ) {
            SplashScreen4(navController = navController)
        }
    }
}