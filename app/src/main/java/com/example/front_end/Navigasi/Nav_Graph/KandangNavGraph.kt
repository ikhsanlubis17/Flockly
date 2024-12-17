package com.example.front_end.Navigasi.Nav_Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.front_end.Navigasi.KANDANG_ROUTE
import com.example.front_end.Navigasi.Screen
import com.example.front_end.R
import com.example.front_end.Screens.EditKandang
import com.example.front_end.Screens.KandangdanRincian
import com.example.front_end.Screens.ProfilKandang
import com.example.front_end.Screens.TambahKandang

fun NavGraphBuilder.kandangNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.KandangdanRincian.route,
        route = KANDANG_ROUTE
    ) {
        composable(route = Screen.KandangdanRincian.route
        ){
            KandangdanRincian(
            navController = navController,
            backIcon = R.drawable.back1,
            searchIcon = R.drawable.search,
                addIcon = android.R.drawable.ic_input_add,
            kandangAyamImage = R.drawable.fotokandang,
            kandangBebekImage = R.drawable.fotokandang,
            kandangPuyuhImage = R.drawable.fotokandang
            )
        }
        composable(route = Screen.ProfilKandang.route){ ProfilKandang(navController = navController) }
        composable(route = Screen.TambahKandang.route){ TambahKandang(navController = navController) }
        composable(route = Screen.EditKandang.route){ EditKandang(navController = navController) }
    }
}