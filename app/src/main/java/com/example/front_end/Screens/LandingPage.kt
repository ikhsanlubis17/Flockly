package com.example.front_end.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.front_end.Navigasi.Screen
import com.example.front_end.R
import com.example.front_end.ui.theme.Front_EndTheme
import kotlinx.coroutines.delay

@Composable
fun LandingPage(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color(0xFFF0F5EE)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.size(350.dp)
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(1000) // Tunggu selama 1 detik
        navController.navigate(Screen.SplashScreen1.route){
            popUpTo(Screen.LandingPage.route){
                inclusive = true
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LandingPagePreview() {
    Front_EndTheme {
        LandingPage(
            navController = TODO()
        )
    }
}
