package com.example.front_end.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.front_end.Navigasi.Screen
import com.example.front_end.R

data class Task(
    val title: String,
    val status: String,
    val date: String,
    val assignee: String,
    val statusColor: Color
)

@Composable
fun Riwayat(
    navController: NavHostController,
    tasks: List<Task>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBF0EC)) // Menambahkan warna latar belakang
            .padding(16.dp)
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            // Ikon Back
            IconButton(
                onClick = {navController.navigate(Screen.Penjadwalan.route)},
                modifier = Modifier.weight(1f, fill = false)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back1), // Ikon dari drawable
                    contentDescription = "Back",
                    modifier = Modifier.size(32.dp), // Perbesar ukuran ikon
                    tint = Color.Black
                )
            }

            // Teks di tengah
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(5f, fill = true)
            ) {
                Text(
                    text = "Riwayat",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.weight(1f, fill = false)) // Spacer untuk memastikan ikon "Back" tidak memengaruhi posisi teks
        }

        // Dropdown Filter
        var filter by remember { mutableStateOf("Semua Waktu") }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            DropdownMenu(filter = filter, onFilterChange = { filter = it })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Task List
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(tasks) { task ->
                TaskCard(task)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun DropdownMenu(filter: String, onFilterChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Button(
            onClick = { expanded = !expanded },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F4A43)),
            modifier = Modifier.width(180.dp) // Lebar tombol dropdown
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = filter,
                    color = Color.White, // Warna teks
                    fontSize = 14.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.dropdown), // Gunakan ikon dropdown
                    contentDescription = "Dropdown",
                    tint = Color.White
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFF5F4A43)) // Warna latar belakang dropdown
        ) {
            listOf("Semua Waktu", "Minggu Ini", "Bulan Ini").forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item,
                            color = Color.White // Warna teks dropdown
                        )
                    },
                    onClick = {
                        onFilterChange(item)
                        expanded = false
                    }
                )
            }
        }
    }
}



@Composable
fun TaskCard(task: Task) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6C3A3)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Task Title and Status Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Task Title
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(task.statusColor, shape = RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = task.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Status
                Text(
                    text = task.status,
                    color = task.statusColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Task Date
            Text(text = if (task.status == "Belum Selesai") "Belum Selesai pada ${task.date}" else "Selesai pada ${task.date}")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RiwayatPreview() {
    val tasks = listOf(
        Task("Pakan Ayam Petelur", "Selesai", "Sep 15, 2023", "", Color(0xFF5F4A43)),
        Task("Vaksin Ayam Pedaging", "Belum Selesai", "Sep 14, 2023", "", Color(0xFFF7F3EA)),
        Task("Pembersihan Kandang Bebek", "Belum Selesai", "Sep 13, 2023", "", Color(0xFFF7F3EA)),
        Task("Pakan Puyuh", "Selesai", "Sep 12, 2023", "", Color(0xFF5F4A43))
    )
    Riwayat(navController = rememberNavController(), tasks = tasks)
}

