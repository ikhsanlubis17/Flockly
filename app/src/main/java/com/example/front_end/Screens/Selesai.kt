package com.example.front_end.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.front_end.Navigasi.Screen
import com.example.front_end.R

@Composable
fun Selesai(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBF0EC)) // Background color
    ) {
        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Add vertical scroll
                .padding(20.dp)
                .padding(top = 54.dp, bottom = 70.dp) // Berikan ruang untuk AppBar dan tombol
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TaskSection("Hari ini", "Maret 15, 2024")
            Spacer(modifier = Modifier.height(16.dp))
            TaskSection("Besok", "Maret 16, 2024")
        }

        // Fixed TopAppBar
        TopAppBarSection(navController = navController)

        // Fixed "Selesaikan" button at the bottom
        FinishButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding() // Adjust for navigation bar
                .padding(16.dp) // Add extra padding for better spacing
        )
    }
}

@Composable
fun TopAppBarSection(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .height(56.dp) // Set height for the AppBar
            .background(Color(0xFFEBF0EC))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp,)
        ) {
            IconButton(onClick = { navController.navigate(Screen.Penjadwalan.route) }) {
                Icon(
                    painter = painterResource(id = R.drawable.back1),
                    contentDescription = "Back",
                    modifier = Modifier.size(32.dp) // Larger icon size
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Selesaikan Tugas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun TaskSection(title: String, date: String) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = date, fontSize = 14.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(8.dp))
        TaskCard(
            iconId = R.drawable.pakan,
            taskTitle = "Pakan Ayam Petelur",
            taskDetail = "9:00 AM - Campuran Jagung dan Dedak Padi"
        )
        TaskCard(
            iconId = R.drawable.vaksin,
            taskTitle = "Vaksin Ayam Pedaging",
            taskDetail = "10:30 AM - Vaksin Newcastle Disease"
        )
        TaskCard(
            iconId = R.drawable.pembersihan,
            taskTitle = "Pembersihan Kandang Bebek",
            taskDetail = "11:00 PM - Pembersihan Harian"
        )
    }
}

@Composable
fun TaskCard(iconId: Int, taskTitle: String, taskDetail: String) {
    val isChecked = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color(0xFFD6C3A3), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = taskTitle, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = taskDetail, fontSize = 14.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color.White, shape = CircleShape)
                .border(1.dp, Color.Gray, shape = CircleShape)
                .clickable {
                    isChecked.value = !isChecked.value
                }
        ) {
            if (isChecked.value) {
                Icon(
                    painter = painterResource(id = R.drawable.centang),
                    contentDescription = "Checked",
                    modifier = Modifier.align(Alignment.Center),
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun FinishButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { /* Handle Finish Task */ },
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F4A43))
    ) {
        Text(text = "Selesaikan", color = Color.White, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun SelesaiPreview() {
    Selesai(navController = rememberNavController())
}
