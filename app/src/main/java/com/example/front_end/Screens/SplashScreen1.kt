package com.example.front_end.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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

@Composable
fun SplashScreen1(
    navController: NavHostController
) {
    Scaffold(
        topBar = {},
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F5EE))
                .padding(innerPadding)
                .clickable {navController.navigate(Screen.LoginScreen.route)}
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 20.dp, end = 15.dp)
                    .align(Alignment.TopEnd)
            ) {
                Text(
                    text = "Lewati",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF000000)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally, // Move this to the Column parameter
                verticalArrangement = Arrangement.Center // Move this to the Column parameter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )

                Spacer(modifier = Modifier.height(20.dp)) // Adds space between logo and text

                Text(
                    text = "Selamat datang di flockly",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(horizontal = 40.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Flockly merupakan aplikasi yang akan membantu Anda dalam monitoring dan pendataan hewan unggas.",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(horizontal = 40.dp)
                )

                Spacer(modifier = Modifier.height(30.dp)) // Adds space before the images below

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tengah1),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Image(
                        modifier = Modifier.clickable{
                            navController.navigate(Screen.SplashScreen2.route)
                        }
                        .size(50.dp),
                        painter = painterResource(id = R.drawable.next),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreen1Preview() {
    Front_EndTheme {
        SplashScreen1(navController = rememberNavController())
    }
}
