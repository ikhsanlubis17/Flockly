package com.example.front_end.Navigasi.BottomNavigasi

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.front_end.R // Sesuaikan dengan paket Anda

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val iconId: Int // Menyimpan ID sumber daya ikon
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        iconId = R.drawable.beranda // Menyimpan ID sumber daya ikon
    )

    object Statistik : BottomBarScreen(
        route = "statistik",
        title = "Statistik",
        iconId = R.drawable.statistik // Menyimpan ID sumber daya ikon
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        iconId = R.drawable.profil // Menyimpan ID sumber daya ikon
    )
}

@Composable
fun BottomBarScreen.icon(): Painter {
    return painterResource(id = iconId) // Mengambil Painter berdasarkan ID ikon
}

