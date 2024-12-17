package com.example.front_end.Navigasi

const val AUTHENTICATION_ROUTE = "authentication"
const val ROOT_ROUTE = "root"
const val SPLASH_ROUTE = "splash"
const val HOME_ROUTE = "homes"
const val PENDATAAN_ROUTE = "pendataans"
const val PENJADWALAN_ROUTE = "penjadwalans"
const val KANDANG_ROUTE = "kandangs"

sealed class Screen (val route : String) {
    object LandingPage : Screen(route = "landing_page")
    object SplashScreen1 : Screen(route = "splash_screen1")
    object SplashScreen2 : Screen(route = "splash_screen2")
    object SplashScreen3 : Screen(route = "splash_screen3")
    object SplashScreen4 : Screen(route = "splash_screen4")
    object LoginScreen : Screen(route = "login")
    object DaftarScreen : Screen(route = "daftar")
    object HomePage : Screen(route = "home")
    object ProfilScreen : Screen (route = "profil")
    object Pendataan : Screen (route = "pendataan")
    object KandangdanRincian : Screen (route = "kandang")
    object DaftarTernak : Screen (route = "daftarternak")
    object HasilProduksi : Screen (route = "hasilproduksi")
    object KonsumsiPakan : Screen (route = "konsumsipakan")
    object Pendapatan : Screen (route = "pendapatan")
    object BiayaOperasional : Screen (route = "biayaoperasional")
    object Penjadwalan : Screen (route = "penjadwalan")
    object TambahJadwal : Screen (route = "tambahjadwal")
    object Selesai : Screen (route = "selesai")
    object Riwayat : Screen (route = "riwayat")
    object ProfilKandang : Screen (route = "profilkandang")
    object MonitoringProduktivitas : Screen (route = "monitoring")
    object Notifikasi : Screen (route = "notifikasi")
    object History : Screen (route = "history")
    object Verifikasi : Screen (route = "verifikasi")
    object TambahKandang : Screen (route = "tambahkandang")
    object EditKandang : Screen (route = "editkandang")
}
