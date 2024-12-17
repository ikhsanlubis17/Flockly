package com.example.front_end.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.front_end.ui.theme.Front_EndTheme

@Composable
fun KonsumsiPakan(navController: NavHostController) {
    val lightGreen = Color(0xFFF0F5EE)
    val buttonBrown = Color(0xFF5F4A43)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = lightGreen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {navController.navigate(Screen.Pendataan.route)},
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back1),
                        contentDescription = "Back",
                        modifier = Modifier.size(40.dp)
                    )
                }
                Text(
                    text = "Konsumsi Pakan",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            YouLivestockInputField(label = "Jumlah Pakan Bulanan", placeholder = "Masukan Jumlah Anda")
            Spacer(modifier = Modifier.height(10.dp))
            YouLivestockInputField(label = "Jumlah Pakan Tahunan", placeholder = "Masukan Jumlah Anda")
            Spacer(modifier = Modifier.height(10.dp))
            YouLivestockInputField(label = "Biaya Konsumsi Pakan Bulanan", placeholder = "Masukan Jumlah Anda")
            Spacer(modifier = Modifier.height(10.dp))
            YouLivestockInputField(label = "Biaya Konsumsi Pakan Tahunan", placeholder = "Masukan Jumlah Anda")

            Spacer(modifier = Modifier.height(80.dp))

            Button(
                onClick = { /* Handle save action */ },
                colors = ButtonDefaults.buttonColors(containerColor = buttonBrown),
                shape = CircleShape
            ) {
                Text(
                    text = "Simpan",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun  YouLivestockInputField(label: String, placeholder: String) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            BasicTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                modifier = Modifier.fillMaxSize(),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 15.sp,
                    color = Color.Gray
                ),
                decorationBox = { innerTextField ->
                    if (textState.value.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KonsumsiPakanPreview() {
    Front_EndTheme {
        KonsumsiPakan(navController = rememberNavController())
    }
}


