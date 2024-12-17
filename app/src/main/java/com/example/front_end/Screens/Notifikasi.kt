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
fun Notifikasi(navController: NavHostController) {
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
                .padding(top = 25.dp, bottom = 16.dp), // Menambahkan padding atas dan bawah
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
        var selectedTab by remember { mutableStateOf("Aktivitas") }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            TabButton("Aktivitas", selectedTab == "Aktivitas") {
                selectedTab = "Aktivitas"
            }
            Spacer(modifier = Modifier.width(8.dp))
            TabButton("History", selectedTab == "History") {
                selectedTab = "History"
                navController.navigate(Screen.History.route) // Tambahkan navigasi ke History
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
            when (selectedTab) {
                "Aktivitas" -> {
                    NotificationList(
                        notifications = listOf(
                            Triple("Pemberian Pakan", "Jangan lupa memberikan pakan pada hewan ternak anda!", "Sekarang"),
                            Triple("Pembersihan Kandang", "Jangan lupa membersihkan kandang hewan ternak anda!", "4 Menit yang lalu")
                        ),
                        sectionTitle = "Hari Ini"
                    )
                }
                "History" -> {
                    NotificationList(
                        notifications = listOf(
                            Triple("Vaksin Hewan Anda", "Jangan lupa memberikan vaksin pada hewan ternak anda!", "Kemarin 08.30"),
                            Triple("Pemberian Pakan", "Jangan lupa memberikan pakan pada hewan ternak anda!", "Kemarin 10.20")
                        ),
                        sectionTitle = "Kemarin"
                    )
                }
            }
        }
    }
}

@Composable
fun TabButton(title: String, isSelected: Boolean, onClick: () -> Unit) {
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
fun NotificationList(notifications: List<Triple<String, String, String>>, sectionTitle: String) {
    // Row untuk menampilkan judul bagian dan tombol "Hapus Semua"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween // Mengatur agar teks dan tombol berada di sisi yang berlawanan
    ) {
        Text(
            text = sectionTitle,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )

        // Tombol "Hapus Semua"
        Text(
            text = "Hapus Semua",
            fontSize = 14.sp,
            color = Color(0xFF5F4A43),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
                    // Aksi ketika tombol "Hapus Semua" diklik
                    // Anda bisa menambahkan logika untuk menghapus semua notifikasi di sini
                    println("Hapus semua notifikasi")
                }
        )
    }

    notifications.forEach { (title, description, time) ->
        NotificationCard(title, description, time)
    }
}


@Composable
fun NotificationCard(title: String, description: String, time: String) {
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
fun PreviewNotifikasi() {
    Notifikasi(navController = rememberNavController())
}


