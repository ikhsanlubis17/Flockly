package com.example.front_end.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.front_end.Navigasi.Screen
import com.example.front_end.R
import com.example.front_end.ui.theme.Front_EndTheme

@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileImageResource: Int,
    editIconResource: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBF0EC))
    ) {
        // Header
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Spacer untuk mengisi ruang di kiri
                Spacer(modifier = Modifier.weight(1f))

                // Title text di tengah
                Text(
                    text = "Profil",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // Spacer untuk mengisi ruang di kanan
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        // Konten Profil
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            UserInfoCard1(editIconResource)
            ProfileImage1(
                profileImageResource,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-60).dp)
            )
        }
    }
}



@Composable
fun ProfileImage1(profileImageResource: Int, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(3.dp, Color(0xFF5F4A43), CircleShape)
                .background(Color.LightGray)
        ) {
            Image(
                painter = painterResource(id = profileImageResource),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Frans Rathore",
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "fransrathore18@gmail.com",
            color = Color.Black
        )
    }
}

@Composable
fun UserInfoCard1(editIconResource: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.90f)
            .clip(RoundedCornerShape(40.dp))
            .background(Color(0xFFD6C3A3))
            .padding(horizontal = 16.dp, vertical = 24.dp) // Menambah padding vertikal
    ) {
        Spacer(modifier = Modifier.height(100.dp)) // Menambah jarak antara atas Card dan konten

        ProfileMenuItem1(
            icon = Icons.Filled.AccountCircle,
            title = "Informasi Akun"
        ) {
            // Handle menu click
        }
        Divider(color = Color.Black, thickness = 1.dp)
        ProfileMenuItem1(
            icon = Icons.Filled.Notifications,
            title = "Pengaturan Notifikasi"
        ) {

        }
        Divider(color = Color.Black, thickness = 1.dp)
        Spacer(modifier = Modifier.height(26.dp)) // Memberikan jarak antara menu dan tombol

        Button(
            onClick = { },
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F4A43))
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = editIconResource),
                    contentDescription = "Edit Icon",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Keluar", color = Color.White)
            }
        }
    }
}


@Composable
fun ProfileMenuItem1(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color(0xFF000000),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    Front_EndTheme {
        ProfileScreen(
            profileImageResource = R.drawable.fotofrans,
            editIconResource = R.drawable.keluar,
            navController = rememberNavController()
        )
    }
}
