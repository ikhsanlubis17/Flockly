package com.example.front_end.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.front_end.ui.theme.Front_EndTheme

@SuppressLint("CustomSplashScreen")
@Composable
fun SplashScreen4(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {},
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F5EE))
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 20.dp, end = 15.dp)
                    .align(Alignment.TopEnd)
                    .clickable {navController.navigate(Screen.LoginScreen.route)}
            ) {
                Text(
                    text = "Lewati",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF000000),
                    modifier = Modifier
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp)
                    .align(Alignment.Center)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splash4),
                        contentDescription = null,
                        modifier = Modifier.size(300.dp)
                    )
                }

                Text(
                    text = "Aplikasi ini dirancang khusus untuk peternak seperti Anda!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(horizontal = 40.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Antarmuka yang sederhana dan intuitif memudahkan Anda untuk mengoperasikan semua fitur. " +
                            "Tidak perlu menjadi ahli teknologi!",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(horizontal = 30.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {navController.navigate(Screen.SplashScreen3.route)}
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Image(
                        painter = painterResource(id = R.drawable.tengah4),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Image(
                        painter = painterResource(id = R.drawable.next),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {navController.navigate(Screen.LoginScreen.route)}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreen4Preview() {
    Front_EndTheme {
        SplashScreen4(navController = rememberNavController())
    }
}
