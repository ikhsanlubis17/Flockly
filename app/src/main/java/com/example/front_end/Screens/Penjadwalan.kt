package com.example.front_end.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.front_end.Navigasi.Screen
import com.example.front_end.R
import com.example.front_end.ui.theme.Front_EndTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Penjadwalan(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Penjadwalan",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(Screen.HomePage.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back1),
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFEBF0EC)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEBF0EC))
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp) // Mengatur jarak antar elemen
        ) {
            UpcomingTasks()
            QuickActions(navController = navController)

            // Logo berada di bawah QuickActions
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // Ganti dengan drawable logo
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(200.dp) // Sesuaikan ukuran logo
                        .background(Color.Transparent, CircleShape), // Background transparan
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
fun UpcomingTasks() {
    // State untuk mengontrol apakah semua tugas ditampilkan atau hanya sebagian
    val showAllTasks = remember { mutableStateOf(false) }

    // Daftar semua tugas
    val tasks = listOf(
        Triple(R.drawable.pakan, "Pakan Ayam Petelur", "9:00 AM - Campuran Jagung dan Dedak Padi"),
        Triple(R.drawable.vaksin, "Vaksin Ayam Pedaging", "10:30 AM - Vaksin Newcastle Disease"),
        Triple(R.drawable.pembersihan, "Pembersihan Kandang Bebek", "11:00 AM - Pembersihan Harian"),
        Triple(R.drawable.pakan, "Pakan Puyuh", "12:00 PM - Pakan Harian"),
        Triple(R.drawable.vaksin, "Vaksin Puyuh", "1:00 PM - Vaksin pertama")
    )

    // Menentukan jumlah tugas yang ditampilkan
    val displayedTasks = if (showAllTasks.value) tasks else tasks.take(3)

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6C3A3)) // Set background color using containerColor
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Tugas Mendatang",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = if (showAllTasks.value) "Tutup" else "Lihat Semua",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.clickable {
                        // Ubah state untuk memperlihatkan atau menyembunyikan semua tugas
                        showAllTasks.value = !showAllTasks.value
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Tampilkan daftar tugas
            displayedTasks.forEach { task ->
                TaskItem(
                    icon = painterResource(id = task.first),
                    title = task.second,
                    description = task.third
                )
            }
        }
    }
}


@Composable
fun TaskItem(icon: Painter, title: String, description: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Ganti Icon dengan Image untuk menggunakan gambar dengan warna asli
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp) // Ukuran ikon diperbesar
                .background(Color.Transparent, CircleShape), // Background transparan
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = title, fontWeight = FontWeight.Medium, color = Color.Black)
            Text(text = description, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun QuickActions(navController: NavHostController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6C3A3)) // Set background color using containerColor
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                QuickActionItem(
                    title = "Jadwal Baru",
                    iconRes = R.drawable.tugasbaru,
                    onClick = { navController.navigate(Screen.TambahJadwal.route) } // Navigasi ke halaman "Tugas Baru"
                )
                QuickActionItem(
                    title = "Selesai",
                    iconRes = R.drawable.selesai,
                    onClick = {navController.navigate(Screen.Selesai.route) }
                )
                QuickActionItem(
                    title = "Riwayat",
                    iconRes = R.drawable.riwayat,
                    onClick = { navController.navigate(Screen.Riwayat.route) }
                )
            }
        }
    }
}

@Composable
fun QuickActionItem(title: String, iconRes: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color(0xFF5F4A43),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = title, fontSize = 12.sp, color = Color.Black)
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewPenjadwalan() {
    Front_EndTheme {
        Penjadwalan(navController = rememberNavController())
    }
}
