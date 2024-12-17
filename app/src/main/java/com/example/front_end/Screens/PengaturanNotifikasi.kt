package com.example.front_end.Screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.example.front_end.R
import com.example.front_end.ui.theme.Front_EndTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PengaturanNotifikasi() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD6C3A3))
    ) {
        // TopAppBar with material3
        TopAppBar(
            title = {
                Text(
                    text = "Pengaturan Notifikasi",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = { /* Add your back navigation logic here */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.back1),
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(35.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFEBF0EC))
        )

        // Decorative canvas at the top
        Box(modifier = Modifier.fillMaxWidth()) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                val path = Path().apply {
                    moveTo(0f, size.height * 0.85f)
                    lineTo(size.width * 0.25f, size.height)
                    lineTo(size.width * 0.6f, size.height * 0.8f)
                    lineTo(size.width * 0.9f, size.height * 0.6f)
                    lineTo(size.width, size.height * 0.7f)
                    lineTo(size.width, 0f)
                    lineTo(0f, 0f)
                    close()
                }
                drawPath(path = path, color = Color(0xFFEBF0EC), style = Fill)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Anda dapat menyesuaikan pengaturan notifikasi\nanda dengan mudah",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // List of notifications settings
        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Column {
                NotificationItem(
                    icon = R.drawable.notiftelur,
                    title = "Notifikasi Telur",
                    description = "Terima pemberitahuan semua notif tentang telur"
                )
                HorizontalDivider(color = Color.Black, thickness = 1.dp) // Ganti Divider dengan HorizontalDivider
                NotificationItem(
                    icon = R.drawable.notifdaging,
                    title = "Notifikasi Daging",
                    description = "Terima pemberitahuan semua notif tentang daging"
                )
                HorizontalDivider(color = Color.Black, thickness = 1.dp)
                NotificationItem(
                    icon = R.drawable.notifkebersihan,
                    title = "Notifikasi Kebersihan",
                    description = "Terima pemberitahuan semua notif tentang kebersihan"
                )
                HorizontalDivider(color = Color.Black, thickness = 1.dp)
                NotificationItem(
                    icon = R.drawable.notifkesehatan,
                    title = "Notifikasi Kesehatan",
                    description = "Terima pemberitahuan semua notif tentang kesehatan"
                )
                HorizontalDivider(color = Color.Black, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun NotificationItem(icon: Int, title: String, description: String) {
    val isEnabled = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000)
            )
            Text(
                text = description,
                fontSize = 10.sp,
                color = Color(0xFF5F4A43)
            )
        }

        Switch(
            checked = isEnabled.value,
            onCheckedChange = { isEnabled.value = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFFFFFFFF),
                uncheckedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF5F4A43),
                uncheckedTrackColor = Color(0xFFEBF0EC)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PengaturanNotifikasiPreview() {
    Front_EndTheme {
        PengaturanNotifikasi()
    }
}
