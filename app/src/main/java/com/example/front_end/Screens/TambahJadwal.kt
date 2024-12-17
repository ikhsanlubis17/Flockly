package com.example.front_end.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.front_end.Navigasi.Screen
import com.example.front_end.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahJadwal(navController: NavHostController) {
    var namaTugas by remember { mutableStateOf(TextFieldValue("")) }
    var kelompokHewan by remember { mutableStateOf("Ayam Pedaging") }
    var tanggalWaktu by remember { mutableStateOf(TextFieldValue("")) }
    var jenisTugas by remember { mutableStateOf("") }
    var catatan by remember { mutableStateOf(TextFieldValue("")) }
    var expanded by remember { mutableStateOf(false) }
    val jenisTernakOptions = listOf("Ayam Pedaging", "Ayam Petelur", "Bebek", "Puyuh")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBF0EC))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(90.dp)) // Ruang untuk TopAppBar agar konten tidak tumpang tindih

            OutlinedTextField(
                value = namaTugas,
                onValueChange = { namaTugas = it },
                label = { Text("Nama Jadwal") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Dropdown untuk Jenis Ternak
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = kelompokHewan,
                    onValueChange = { kelompokHewan = it },
                    label = { Text("Jenis Ternak") },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    readOnly = true,
                    trailingIcon = {
                        // Replace with your custom drawable icon
                        Icon(
                            painter = painterResource(id = R.drawable.dropdown), // Replace with your drawable
                            contentDescription = null,
                            tint = Color.Black // Set tint color
                        )
                    }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    jenisTernakOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                kelompokHewan = option
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = tanggalWaktu,
                onValueChange = { tanggalWaktu = it },
                label = { Text("Tanggal & Waktu") },
                placeholder = { Text("dd/mm/yyyy --.--") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        tint = Color.Black // Set tint ke hitam
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )


            Text(
                text = "Jenis Jadwal",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButtonWithLabel("Pemberian Pakan", jenisTugas, onValueChange = { jenisTugas = it })
                RadioButtonWithLabel("Pembersihan Kandang", jenisTugas, onValueChange = { jenisTugas = it })
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButtonWithLabel("Vaksinasi", jenisTugas, onValueChange = { jenisTugas = it })
            }

            OutlinedTextField(
                value = catatan,
                onValueChange = { catatan = it },
                label = { Text("Catatan") },
                placeholder = { Text("Tambahkan catatan tambahan apa pun") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { /* Handle Buat Tugas */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F4A43))
                ) {
                    Text("Tambah", color = Color.White)
                }

                OutlinedButton(
                    onClick = { /* Handle Membatalkan */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Batal",color = Color.Black)
                }
            }
        }

        // TopAppBar presisi
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "Tambah Jadwal",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = {navController.navigate(Screen.Penjadwalan.route)  }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back1),
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.size(35.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFFEBF0EC))
        )

    }
}


@Composable
fun RadioButtonWithLabel(label: String, selectedValue: String, onValueChange: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        RadioButton(
            selected = selectedValue == label,
            onClick = { onValueChange(label) },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF5F4A43), // Warna ketika dipilih
                unselectedColor = Color.Gray // Warna ketika tidak dipilih
            )
        )
        Text(label)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTambahJadwal() {
    TambahJadwal(navController = rememberNavController())
}
