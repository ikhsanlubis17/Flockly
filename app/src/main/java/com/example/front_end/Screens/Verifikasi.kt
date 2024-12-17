package com.example.front_end.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.front_end.R
import com.example.front_end.ui.theme.Front_EndTheme


@Composable
fun Verifikasi(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBF0EC))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var otpText by remember { mutableStateOf("") }
        val otpCount = 4

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Flockly",
            modifier = Modifier.size(200.dp)
        )

        Text(
            text = "Verifikasi Alamat Email",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Masukan kode verifikasi dari alamat email anda, silahkan periksa email anda.",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // OTP Text Field
        BasicTextField(
            modifier = Modifier,
            value = otpText,
            onValueChange = { newText ->
                if (newText.length <= otpCount) {
                    otpText = newText
                }
            },

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.Center) {
                    repeat(otpCount) { index ->
                        CharView(
                            index = index,
                            text = otpText
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { },
            modifier = Modifier.width(220.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5F4A43)
            )
        ) {
            Text(
                text = "Verifikasi",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text(text = "Belum Menerima Kode Verifikasi?", fontSize = 13.sp)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Kirim Ulang Kode", color = Color(0xFF5F4A43), fontSize = 13.sp)
        }
    }
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val char = when {
        index < text.length -> text[index].toString()
        else -> ""
    }

    Text(
        modifier = Modifier
            .width(55.dp)
            .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(15.dp),
        text = char,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center
    )
}

// Preview Section
@Preview(showBackground = true)
@Composable
fun VerifikasiPreview() {
    Front_EndTheme() {
        Verifikasi(navController = rememberNavController())
    }
}
