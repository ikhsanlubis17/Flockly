package com.example.front_end.Screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.front_end.ui.theme.Front_EndTheme
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val location = remember { mutableStateOf("Cipondoh, Tangerang, Indonesia") }

    // Update lokasi saat aplikasi dijalankan
    LaunchedEffect(Unit) {
        val ctx = context.applicationContext
        val newLocation = getCurrentLocation(ctx) ?: location.value
        location.value = newLocation
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBF0EC))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 40.dp)
                .padding(bottom = 100.dp)
        ) {
            HeaderSection(navController = navController)
            Spacer(modifier = Modifier.height(10.dp))
            ReminderCard(location = location.value)
            Spacer(modifier = Modifier.height(10.dp))
            StatisticSection()
            Spacer(modifier = Modifier.height(10.dp))
            FeatureSection(navController = navController)
        }
    }
}

@Composable
fun HeaderSection(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Hallo, Frans Rathore!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                .clickable {navController.navigate(Screen.Notifikasi.route)},
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notifikasi),
                contentDescription = "Notification",
                tint = Color(0xFF5F4A43),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReminderCard(location: String) {
    var currentTime by remember { mutableStateOf(LocalTime.now()) }

    // Update waktu setiap detik
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalTime.now()
            delay(1000L)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD6C3A3), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Jangan lupa jadwal hari ini!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Vaksin Ayam",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = currentTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = location,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

fun getCurrentLocation(context: Context): String? {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        location?.let {
            return "${it.latitude}, ${it.longitude}"
        }
    }
    return null
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatisticSection() {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf("Data Populasi") } // State untuk tab yang dipilih

    val currentDate = LocalDate.now()
    val dayOfWeek = currentDate.dayOfWeek
        .getDisplayName(TextStyle.FULL, Locale("id", "ID"))
    val dayOfMonth = currentDate.dayOfMonth
    val currentMonth = currentDate.month
        .getDisplayName(TextStyle.FULL, Locale("id", "ID"))
    val currentYear = currentDate.year // Tahun

    Column {
        Text(
            text = "Ringkasan Statistik Ternak",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "$dayOfWeek, $dayOfMonth $currentMonth $currentYear",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(2.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                StatisticButton(
                    text = "Data Populasi",
                    isSelected = selectedTab == "Data Populasi",
                    onClick = { setSelectedTab("Data Populasi") }
                )
            }
            item {
                StatisticButton(
                    text = "Hasil Produksi",
                    isSelected = selectedTab == "Hasil Produksi",
                    onClick = { setSelectedTab("Hasil Produksi") }
                )
            }
            item {
                StatisticButton(
                    text = "Kebersihan Kandang",
                    isSelected = selectedTab == "Kebersihan Kandang",
                    onClick = { setSelectedTab("Kebersihan Kandang") }
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Konten yang ditampilkan berdasarkan tab yang dipilih
        when (selectedTab) {
            "Data Populasi" -> LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                item { StatisticCard(imageRes = R.drawable.ayampedaging, title = "Ayam Pedaging", value = "250 Ekor") }
                item { StatisticCard(imageRes = R.drawable.ayampetelur, title = "Ayam Petelur", value = "300 Ekor") }
                item { StatisticCard(imageRes = R.drawable.bebek, title = "Bebek", value = "300 Ekor") }
                item { StatisticCard(imageRes = R.drawable.puyuh, title = "Puyuh", value = "300 Ekor") }
            }
            "Hasil Produksi" -> LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                item { StatisticCard(imageRes = R.drawable.dagingayam, title = "Daging Ayam", value = "250 Kg") }
                item { StatisticCard(imageRes = R.drawable.telurayam, title = "Telur Ayam", value = "300 Butir") }
                item { StatisticCard(imageRes = R.drawable.telurbebek, title = "Telur Bebek", value = "300 Butir") }
                item { StatisticCard(imageRes = R.drawable.telurpuyuh, title = "Telur Puyuh", value = "300 Butir") }
            }
            "Kebersihan Kandang" -> LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                item { StatisticCard(title = "Kandang Ayam", subtitle = "Cipondoh, RT 010/004 Tangerang", status = "Selesai") }
                item { StatisticCard(title = "Kandang Bebek", subtitle = "Cipondoh, RT 010/004 Tangerang", status = "Selesai") }
                item { StatisticCard(title = "Kandang Bebek", subtitle = "Cipondoh, RT 010/004 Tangerang", status = "Belum Selesai") }
            }
        }

    }
}

@Composable
fun StatisticButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF5F4A43) else Color.White
        ),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 15.dp,
            pressedElevation = 10.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

@Composable
fun StatisticCard(
    imageRes: Int? = null, // Default null untuk mendukung kartu tanpa gambar
    title: String,
    value: String? = null, // Default null untuk mendukung kartu tanpa nilai
    subtitle: String? = null, // Subtitle opsional
    status: String? = null // Status opsional
    ) {
    Row(
        modifier = Modifier.width(230.dp).background(Color.White, RoundedCornerShape(20.dp)).padding(20.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        if (imageRes != null) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        Column {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            if (value != null) {
                Text(
                    text = value,
                    fontSize = 12.sp
                )
            }
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            if (status != null) {
                Text(
                    text = status,
                    fontSize = 14.sp,
                    color = if (status == "Selesai") Color(0xFF5F4A43) else Color(0xFFF7F3EA),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun FeatureSection(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize() // Memastikan bahwa kolom ini mengisi seluruh ukuran layar dan bisa digulir secara vertikal
            .verticalScroll(rememberScrollState()) // Menambahkan kemampuan untuk scroll vertikal
    ) {
        Text(
            text = "Fitur Aplikasi",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), // Memberikan padding untuk ruang di kiri dan kanan
            horizontalArrangement = Arrangement.spacedBy(10.dp) // Memberikan jarak antar item secara horizontal
        ) {
            item {
                FeatureItem(
                    text = "Pendataan",
                    imageRes = R.drawable.pendataan,
                    onClick = {navController.navigate(Screen.Pendataan.route)} // Navigasi ke halaman pendataan
                )
            }
            item {
                FeatureItem(
                    text = "Kandang",
                    imageRes = R.drawable.kandang,
                    onClick = {navController.navigate(Screen.KandangdanRincian.route)}
                )
            }
            item {
                FeatureItem(
                    text = "Penjadwalan",
                    imageRes = R.drawable.penjadwalan,
                    onClick = {navController.navigate(Screen.Penjadwalan.route)}
                )
            }
            item {
                FeatureItem(
                    text = "Monitoring\nProduktivitas",
                    imageRes = R.drawable.monitoring,
                    onClick = { navController.navigate(Screen.MonitoringProduktivitas.route) }
                )
            }
        }
    }
}

@Composable
fun FeatureItem(text: String, imageRes: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentWidth()
            .padding(5.dp) // Menambahkan padding antara setiap item
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
                .background(Color.White, RoundedCornerShape(20.dp))
                .clickable {onClick()},
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = text,
                modifier = Modifier.size(60.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePagePreview() {
    Front_EndTheme {
        HomePage(
            navController = rememberNavController())
    }
}
