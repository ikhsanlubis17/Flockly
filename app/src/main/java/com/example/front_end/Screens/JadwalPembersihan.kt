package com.example.front_end.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.front_end.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JadwalPembersihan() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Jadwal Pembersihan",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back action */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back1), // Replace with your drawable resource
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier.size(35.dp) // Perbesar ukuran ikon
                        )

                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFEBF0EC) // Top bar background color
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFEBF0EC)) // Set the screen background color
                    .padding(innerPadding)
                    .padding(10.dp)
            ) {
                // Jadwal Section
                ScheduleSection()
                Spacer(modifier = Modifier.height(16.dp))
                // History Section
                HistorySection()
            }
        }
    )
}
@Composable
fun ScheduleSection() {
    val days = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
    val selectedDay = remember { mutableIntStateOf(0) } // Menyimpan indeks hari yang dipilih

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp), // Menambahkan jarak antar elemen
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()) // Mengaktifkan scroll horizontal
                .padding(vertical = 8.dp) // Kurangi padding atas dan bawah
        ) {
            days.forEachIndexed { index, day ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp) // Memberikan jarak antar elemen
                        .background(
                            color = if (selectedDay.intValue == index) Color(0xFFD6C3A3) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { selectedDay.intValue = index } // Menangani klik untuk memilih hari
                        .padding(horizontal = 12.dp, vertical = 8.dp) // Memberikan padding dalam
                ) {
                    Text(
                        text = day,
                        fontSize = 14.sp, // Ukuran teks yang cukup
                        color = if (selectedDay.intValue == index) Color.White else Color.Black,
                        fontWeight = if (selectedDay.intValue == index) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp)) // Jarak antara elemen Row dan jadwal
        ScheduleCard("Kandang Ayam", "08:20", "Jangan lupa memberikan pakan pada hewan ternak anda!")
        ScheduleCard("Kandang Puyuh", "12:30", "Jangan lupa memberikan pakan pada hewan ternak anda!")
        ScheduleCard("Kandang Bebek", "15:40", "Jangan lupa memberikan pakan pada hewan ternak anda!")
        Button(
            onClick = { /* Add new schedule logic */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F4A43))
        ) {
            Text(text = "Tambah Jadwal Baru", color = Color.White)
        }
    }
}


@Composable
fun ScheduleCard(title: String, time: String, description: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(Color(0xFF5F4A43), shape = RoundedCornerShape(50))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .background(Color(0xFFD6C3A3), shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = time, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description, fontSize = 14.sp)
        }
    }
}

@Composable
fun HistorySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
            )
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp) // Tambahkan padding atas
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween, // Atur agar teks diratakan ke kiri dan kanan
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "History",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "2 Notifikasi Baru",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            HistoryCard("4 Okt", "Kandang ayam sudah dibersihkan")
            HistoryCard("4 Okt", "Kandang puyuh sudah dibersihkan")
        }
    }
}




@Composable
fun HistoryCard(date: String, message: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp) // Hanya jarak antar card
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(Color(0xFFD6C3A3), shape = RoundedCornerShape(50))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                .padding(16.dp) // Padding internal
        ) {
            Text(text = date, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = message, fontSize = 14.sp)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewJadwalPembersihan() {
    JadwalPembersihan()
}
