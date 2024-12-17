package com.example.front_end.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.front_end.Navigasi.Screen
import com.example.front_end.R

@Composable
fun History(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBF0EC)) // Latar belakang layar
            .padding(16.dp)
    ) {
        // Header tetap di atas (tidak tergulir)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 16.dp), // Menambahkan padding atas dan bawah
            contentAlignment = Alignment.Center
        ) {
            // Back Icon
            IconButton(
                onClick = { navController.navigate(Screen.HomePage.route) },
                modifier = Modifier.align(Alignment.CenterStart) // Tempatkan ikon di sebelah kiri
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back1), // Ganti dengan drawable Anda
                    contentDescription = "Back Icon",
                    modifier = Modifier.size(36.dp) // Perbesar ukuran ikon
                )
            }
            // Header Title
            Text(
                text = "Notifikasi",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        // Tabs
        var selectedTab by remember { mutableStateOf("History") }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            TabButton1("Aktivitas", selectedTab == "Aktivitas") {
                navController.navigate(Screen.Notifikasi.route) // Tambahkan navigasi ke History
                selectedTab = "Aktivitas"
            }
            Spacer(modifier = Modifier.width(8.dp))
            TabButton1("History", selectedTab == "History") {
                selectedTab = "History"
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Konten yang bisa digulir
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Membuat konten bisa digulir
        ) {
            // Notification List berdasarkan tab yang dipilih
            NotificationList1(
                notifications = listOf(
                    Triple("Pemberian Pakan", "Jangan lupa memberikan pakan pada hewan ternak anda!", "Sekarang"),
                    Triple("Pembersihan Kandang", "Jangan lupa membersihkan kandang hewan ternak anda!", "4 Menit yang lalu")
                ),
                sectionTitle = "Hari Ini"
            )

            NotificationList1(
                notifications = listOf(
                    Triple("Vaksin Hewan Anda", "Jangan lupa memberikan vaksin pada hewan ternak anda!", "Kemarin 08.30"),
                    Triple("Pemberian Pakan", "Jangan lupa memberikan pakan pada hewan ternak anda!", "Kemarin 10.20")
                ),
                sectionTitle = "Kemarin"
            )

            NotificationList1(
                notifications = listOf(
                    Triple("Cek Kesehatan", "Jangan lupa melakukan pengecekan pada hewan ternak anda!", "Kamis 09.30")
                ),
                sectionTitle = "Kamis"
            )
        }
    }
}

@Composable
fun TabButton1(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF5F4A43) else Color.Gray,
            contentColor = if (isSelected) Color.White else Color.Black
        ),
        shape = RoundedCornerShape(50),
        modifier = Modifier.width(120.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }
}

@Composable
fun NotificationList1(notifications: List<Triple<String, String, String>>, sectionTitle: String) {
    // Row untuk menampilkan judul bagian dan tombol "Hapus Semua" di sebelah kanan
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween // Menempatkan teks di kiri dan tombol di kanan
    ) {
        // Teks judul bagian (contoh "Hari Ini")
        Text(
            text = sectionTitle,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f) // Membuat teks mengambil ruang tersisa di kiri
        )

        // Tombol "Hapus Semua" di sebelah kanan
        Text(
            text = "Hapus Semua",
            fontSize = 14.sp,
            color = Color(0xFF5F4A43),
            modifier = Modifier
                .clickable {
                    // Aksi ketika tombol "Hapus Semua" diklik
                    println("Hapus semua notifikasi")
                }
                .align(Alignment.CenterVertically) // Memastikan tombol rata vertikal dengan teks
        )
    }

    // Loop untuk menampilkan notifikasi
    notifications.forEach { (title, description, time) ->
        NotificationCard1(title, description, time)
    }
}


@Composable
fun NotificationCard1(title: String, description: String, time: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6C3A3))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Image(
                painter = painterResource(id = R.drawable.notifikasi), // Ganti dengan drawable Anda
                contentDescription = "Notification Icon",
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Divider Line
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .width(1.dp)
                    .height(48.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Title and Description
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = description, fontSize = 14.sp)
            }

            // Time
            Text(
                text = time,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.End,
                modifier = Modifier.align(Alignment.Top)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistory() {
    History(navController = rememberNavController())
}


